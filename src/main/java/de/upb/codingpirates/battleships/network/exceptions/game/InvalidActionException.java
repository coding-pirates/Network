package de.upb.codingpirates.battleships.network.exceptions.game;

import de.upb.codingpirates.battleships.logic.util.ErrorType;

public class InvalidActionException extends GameException {
    public InvalidActionException(String message) {
        super(message, ErrorType.INVALID_ACTION);
    }
}
