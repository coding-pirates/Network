package de.upb.codingpirates.battleships.network.exceptions.parser;

import de.upb.codingpirates.battleships.logic.ErrorType;

public class BadMessageException extends ParserException {

    public BadMessageException(String message) {
        super(message, ErrorType.BAD_MESSAGE);
    }


}
