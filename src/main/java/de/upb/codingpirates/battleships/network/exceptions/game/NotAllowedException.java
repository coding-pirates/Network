package de.upb.codingpirates.battleships.network.exceptions.game;

import de.upb.codingpirates.battleships.logic.ErrorType;

public class NotAllowedException extends GameException {
    public NotAllowedException(String message) {
        super(message, ErrorType.NOT_ALLOWED);
    }
}
