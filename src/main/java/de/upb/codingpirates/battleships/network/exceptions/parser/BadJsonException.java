package de.upb.codingpirates.battleships.network.exceptions.parser;

import de.upb.codingpirates.battleships.logic.util.ErrorType;

public class BadJsonException extends ParserException {

    public BadJsonException(String message) {
        super(message, ErrorType.BAD_JSON);
    }

}
