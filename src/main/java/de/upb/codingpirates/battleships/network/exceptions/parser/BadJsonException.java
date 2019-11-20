package de.upb.codingpirates.battleships.network.exceptions.parser;

import de.upb.codingpirates.battleships.logic.util.ErrorType;
import de.upb.codingpirates.battleships.network.id.Id;

import javax.annotation.Nonnull;

public class BadJsonException extends ParserException {

    public BadJsonException(String message) {
        super(message, ErrorType.BAD_JSON);
    }

    public BadJsonException(@Nonnull String message, @Nonnull Id connectionId) {
        this(message);
        this.setConnectionId(connectionId);
    }

    public BadJsonException(@Nonnull String message, @Nonnull Id connectionId, int messageId) {
        this(message, connectionId);
        this.setMessageId(messageId);
    }

}
