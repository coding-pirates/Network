package de.upb.codingpirates.battleships.network.exceptions.game;

import de.upb.codingpirates.battleships.logic.util.ErrorType;
import de.upb.codingpirates.battleships.network.id.Id;

import javax.annotation.Nonnull;

public class NotAllowedException extends GameException {
    public NotAllowedException(String message) {
        super(message, ErrorType.NOT_ALLOWED);
    }

    public NotAllowedException(@Nonnull String message, @Nonnull Id connectionId) {
        this(message);
        this.setConnectionId(connectionId);
    }

    public NotAllowedException(@Nonnull String message, @Nonnull Id connectionId, int messageId) {
        this(message, connectionId);
        this.setMessageId(messageId);
    }
}
