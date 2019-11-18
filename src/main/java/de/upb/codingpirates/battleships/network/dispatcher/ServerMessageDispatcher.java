package de.upb.codingpirates.battleships.network.dispatcher;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.MessageHandler;
import de.upb.codingpirates.battleships.network.network.ServerNetwork;
import de.upb.codingpirates.battleships.network.scope.ConnectionScope;
import io.reactivex.Observable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@link ServerMessageDispatcher} registers a read loop for the {@link Observable} of the {@link ServerNetwork}. The read loop {@link ServerMessageDispatcher#dispatch(Pair)} a request if it receives a message.
 * @author Paul Becker
 */
public class ServerMessageDispatcher implements MessageDispatcher {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ConnectionScope scope;
    private final Injector injector;

    @Inject
    public ServerMessageDispatcher(ServerNetwork network, Injector injector, ConnectionScope scope) {
        LOGGER.debug("Initialize server message dispatcher");

        this.scope = scope;
        this.injector = injector;

        if (network == null || network.isClosed()) {
            LOGGER.warn("Server network is not working");
            return;
        }

        network.getConnections().subscribe(this::readLoop);
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
            type = Class.forName("de.upb.codingpirates.battleships.server.handler." + name + "Handler");
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
}
