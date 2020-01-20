package de.upb.codingpirates.battleships.network.dispatcher;

import com.google.inject.Injector;
import de.upb.codingpirates.battleships.logic.util.Dist;
import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.exceptions.game.GameException;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.handler.MessageHandler;
import de.upb.codingpirates.battleships.network.scope.ConnectionScope;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Necessary for Guice to get a class depending on distribution.
 *
 * @author Paul Becker
 */
public interface MessageDispatcher {

    @SuppressWarnings("unchecked")
    default void handleMessage(Pair<Connection, Message> request, Dist dist, ConnectionScope scope, Injector injector, Logger LOGGER) throws GameException, ClassNotFoundException, IOException {
        LOGGER.info(request.getValue());
        String[] namespace = request.getValue().getClass().getName().split("\\.");
        String name = namespace[namespace.length - 1];
        Class<?> type;
        type = Class.forName(dist.getMessageHandlerPackage() + "." + name + "Handler");
        scope.seed(Connection.class, request.getKey());
        scope.enter(request.getKey().getId());

        MessageHandler handler = (MessageHandler) injector.getInstance(type);
        if (handler == null) {
            LOGGER.info("Can't find MessageHandler {} for Message {}", type, request.getValue().getClass());
        } else {
            if (handler.canHandle(request.getValue())) {
                handler.handle(request.getValue(), request.getKey().getId());
            }
        }
    }

}
