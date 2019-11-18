package de.upb.codingpirates.battleships.network.dispatcher;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.annotations.bindings.CachedThreadPool;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.MessageHandler;
import de.upb.codingpirates.battleships.network.network.ClientNetwork;
import de.upb.codingpirates.battleships.network.scope.ConnectionScope;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * The {@link ClientMessageDispatcher} registers a read loop for the {@link Observable} of the {@link ClientNetwork}. The read loop {@link ClientMessageDispatcher#dispatch(Pair)} a request if it receives a message.
 *
 * @author Paul Becker
 */
public class ClientMessageDispatcher implements MessageDispatcher {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ClientNetwork network;
    private Connection connection;
    private final Scheduler scheduler;
    private final ConnectionScope scope;
    private final Injector injector;

    @Inject
    public ClientMessageDispatcher(@CachedThreadPool ExecutorService executorService, Injector injector, ConnectionScope scope, ClientNetwork clientNetwork) {
        this.scheduler = Schedulers.from(executorService);
        this.scope = scope;
        this.network = clientNetwork;
        this.injector = injector;
    }

    public Connection connect(@Nonnull String host, int port) throws IOException {
        this.connection = this.network.connect(host, port);

        Observable<Connection> observer = Observable.create(this::setConnection);
        observer.subscribeOn(this.scheduler).subscribe(this::readLoop);
        this.network.setObserver(observer);
        return connection;
    }

    private void readLoop(Connection connection) {
        LOGGER.debug("Connection from {}", connection.getInetAdress());
        this.loop(connection, this::dispatch, this::error);
    }

    /**
     * Called if the {@link Observable} gets a message.
     * <p></p>
     * <p>
     * It tries to get a {@link MessageHandler} based on the name of the message and tries to let the MessageHandler handle the message. Otherwise logs th failure.
     *
     * @param request
     */
    private void dispatch(Pair<Connection, Message> request) {
        try {
            String[] namespace = request.getValue().getClass().getName().split("\\.");
            String name = namespace[namespace.length - 1];
            Class<?> type;
            type = Class.forName("de.upb.codingpirates.battleships.client.handler." + name + "Handler");
            this.scope.seed(Connection.class, request.getKey());
            this.scope.enter(request.getKey().getId());

            MessageHandler handler = (MessageHandler) injector.getInstance(type);
            if (handler == null) {
                LOGGER.error("Can't find MessageHandler {} for Message {}", type, request.getValue().getClass());
            } else {
                if (handler.canHandle(request.getValue())) {
                    handler.handle(request.getValue());
                }
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Can't find MessageHandler for Message", e);
        } finally {
            this.scope.exit();
        }
    }

    private void error(Throwable throwable) {
        LOGGER.error("Error while reading Messages on Server", throwable);
    }

    /**
     * This sets an Observable to observe only the connection from the server.
     */
    private void setConnection(ObservableEmitter<Connection> emitter) {
        emitter.onNext(this.connection);
    }
}
