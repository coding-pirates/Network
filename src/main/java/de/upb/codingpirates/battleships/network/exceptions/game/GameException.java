package de.upb.codingpirates.battleships.network.exceptions.game;

import de.upb.codingpirates.battleships.logic.ErrorType;
import de.upb.codingpirates.battleships.network.exceptions.BattleshipException;

import javax.annotation.Nonnull;

public abstract class GameException extends BattleshipException {
    public GameException(@Nonnull String message, @Nonnull ErrorType errorType) {
        super(message, errorType);
    }
}
