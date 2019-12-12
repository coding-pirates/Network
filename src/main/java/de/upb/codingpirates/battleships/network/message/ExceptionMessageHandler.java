package de.upb.codingpirates.battleships.network.message;

import de.upb.codingpirates.battleships.network.exceptions.game.GameException;
import de.upb.codingpirates.battleships.network.id.Id;

public abstract class ExceptionMessageHandler<T extends Message> implements MessageHandler<T> {
    @Override
    public final void handle(T message, Id connectionId) throws GameException {
        try {
            handleMessage(message, connectionId);
        } catch (GameException e) {
            e.setMessageId(message.getMessageId());
            e.setConnectionId(connectionId);
            throw e;
        }
    }

    abstract protected void handleMessage(T message, Id connectionId) throws GameException;
}
