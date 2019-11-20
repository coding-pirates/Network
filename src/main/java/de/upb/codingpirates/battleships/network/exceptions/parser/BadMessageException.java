package de.upb.codingpirates.battleships.network.exceptions.parser;

import de.upb.codingpirates.battleships.logic.util.ErrorType;
import de.upb.codingpirates.battleships.network.id.Id;

import javax.annotation.Nonnull;

public class BadMessageException extends ParserException {

    public BadMessageException(String message) {
        super(message, ErrorType.BAD_MESSAGE);
    }

    public BadMessageException(@Nonnull String message, @Nonnull Id connectionId) {
        this(message);
        this.setConnectionId(connectionId);
    }

    public BadMessageException(@Nonnull String message, @Nonnull Id connectionId, int messageId) {
        this(message, connectionId);
        this.setMessageId(messageId);
    }

}
