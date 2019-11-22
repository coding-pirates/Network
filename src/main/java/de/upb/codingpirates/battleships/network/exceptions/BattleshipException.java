package de.upb.codingpirates.battleships.network.exceptions;

import de.upb.codingpirates.battleships.logic.util.ErrorType;
import de.upb.codingpirates.battleships.network.id.Id;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BattleshipException extends Exception {
    @Nullable
    private Id connectionId;
    private ErrorType errorType;
    private int messageId;

    public BattleshipException(@Nonnull String message, @Nonnull ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public void setConnectionId(@Nullable Id connectionId) {
        this.connectionId = connectionId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Nonnull
    public ErrorType getErrorType() {
        return errorType;
    }

    @Nullable
    public Id getConnectionId() {
        return connectionId;
    }

    public int getMessageId() {
        return messageId;
    }
}
