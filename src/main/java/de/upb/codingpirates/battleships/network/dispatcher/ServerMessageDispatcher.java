package de.upb.codingpirates.battleships.network.dispatcher;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.ConnectionManager;
import de.upb.codingpirates.battleships.network.annotations.bindings.CachedThreadPool;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.MessageHandler;
import de.upb.codingpirates.battleships.network.message.Request;
import de.upb.codingpirates.battleships.network.network.ServerNetwork;
import de.upb.codingpirates.battleships.network.scope.ConnectionScope;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @author Paul Becker
 */
public class ServerMessageDispatcher implements MessageDispatcher {
    private static final Logger LOGGER = LogManager.getLogger();

    private ConnectionManager connectionManager;
    private ConnectionScope scope;
    private ServerNetwork network;
    private Injector injector;
    private ExecutorService executorService;

    @Inject
    public ServerMessageDispatcher(ServerNetwork network, @CachedThreadPool ExecutorService executorService, Injector injector, ConnectionManager connectionManager, ConnectionScope scope) {
        LOGGER.debug("Initialize server message dispatcher");

        this.connectionManager = connectionManager;
        this.scope = scope;
        this.network = network;
        this.injector = injector;
        this.executorService = executorService;

        if (network == null || network.isClosed()) {
            LOGGER.warn("Server network is not working");
        }

        this.network.getConnections().subscribe(this::readLoop);
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
        }).subscribeOn(Schedulers.from(executorService)).subscribe(this::dispatch, this::error);
    }

    public void dispatch(Request request) {
        try {
            String[] namespace = request.getMessage().getClass().getName().split("\\.");
            String name = namespace[namespace.length - 1];
            Class<?> type;
            type = Class.forName(
                    "de.upb.codingpirates.battleships.server.handler."
                            + name
                            + "Handler");
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
}
