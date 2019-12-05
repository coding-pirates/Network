package de.upb.codingpirates.battleships.network.exceptions.parser;

import de.upb.codingpirates.battleships.logic.ErrorType;
import de.upb.codingpirates.battleships.network.exceptions.BattleshipException;

import javax.annotation.Nonnull;

@SuppressWarnings("WeakerAccess")
public abstract class ParserException extends BattleshipException {
    protected ParserException(@Nonnull String message, @Nonnull ErrorType errorType) {
        super(message, errorType);
    }
}