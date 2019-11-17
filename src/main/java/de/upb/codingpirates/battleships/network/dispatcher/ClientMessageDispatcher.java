package de.upb.codingpirates.battleships.network.dispatcher;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.annotations.bindings.CachedThreadPool;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.MessageHandler;
import de.upb.codingpirates.battleships.network.message.Request;
import de.upb.codingpirates.battleships.network.network.ClientNetwork;
import de.upb.codingpirates.battleships.network.scope.ConnectionScope;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @author Paul Becker
 */
public class ClientMessageDispatcher implements MessageDispatcher {
    private static final Logger LOGGER = LogManager.getLogger();

    private ClientNetwork network;
    private Connection connection;
    private Scheduler scheduler;
    private ConnectionScope scope;
    private Injector injector;

    @Inject
    public ClientMessageDispatcher(@CachedThreadPool ExecutorService executorService, Injector injector, ConnectionScope scope, ClientNetwork clientNetwork) {
        this.scheduler = Schedulers.from(executorService);
        this.scope = scope;
        this.network = clientNetwork;
        this.injector = injector;
    }

    public Connection connect(String host, int port) throws IOException {
        this.connection = this.network.connect(host, port);

        Observable<Connection> observer = Observable.create(this::setConnection);
        observer.subscribeOn(this.scheduler).subscribe(this::readLoop);
        this.network.setObserver(observer);
        return connection;
    }

    private void readLoop(Connection connection) {
        LOGGER.debug("Connection from {}", connection.getInetAdress());

        Observable.create((ObservableEmitter<Request> emitter) -> {
            while (!connection.isClosed()) {
                try {
                    Message message = connection.read();
                    Request request = new Request(connection, message);
                    emitter.onNext(request);
                } catch (IOException e) {
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe(this::dispatch, this::error);
    }

    private void dispatch(Request request) {
        try {
            String[] namespace = request.getMessage().getClass().getName().split("\\.");
            String name = namespace[namespace.length - 1];
            Class<?> type;
            type = Class.forName("de.upb.codingpirates.battleships.client.handler." + name + "Handler");
            this.scope.seed(Connection.class, request.getConnection());
            this.scope.enter(request.getConnection().getId());

            MessageHandler handler = (MessageHandler) injector.getInstance(type);
            if (handler == null) {
                LOGGER.error("Can't find MessageHandler {} for Message {}", type, request.getMessage().getClass());
            } else {
                if (handler.canHandle(request.getMessage())) {
                    handler.handle(request.getMessage());
                }
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Can't find MessageHandler for Message", e);
        } finally {
            this.scope.exit();
        }
    }

    public void error(Throwable throwable) {
        LOGGER.error("Error while reading Messages on Server", throwable);
    }

    public void setConnection(ObservableEmitter<Connection> emitter) {
        emitter.onNext(this.connection);
    }
}
