package de.upb.codingpirates.battleships.network.exceptions.game;

import de.upb.codingpirates.battleships.logic.util.ErrorType;
import de.upb.codingpirates.battleships.network.id.Id;

import javax.annotation.Nonnull;

public class InvalidActionException extends GameException {
    public InvalidActionException(String message) {
        super(message, ErrorType.INVALID_ACTION);
    }

    public InvalidActionException(@Nonnull String message, @Nonnull Id connectionId) {
        this(message);
        this.setConnectionId(connectionId);
    }

    public InvalidActionException(@Nonnull String message, @Nonnull Id connectionId, int messageId) {
        this(message, connectionId);
        this.setMessageId(messageId);
    }
}
