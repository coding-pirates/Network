package de.upb.codingpirates.battleships.network.message.notification;

import com.google.gson.annotations.SerializedName;
import de.upb.codingpirates.battleships.logic.ErrorType;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;

/**
 * Defines a client-initiated error.
 *
 * @author Interdoc committee & Paul Becker
 */
public class ErrorNotification extends Message {

    public static final int MESSAGE_ID = 999;

    /**
     * Specifies the error type
     */
    @Nonnull
    @SerializedName("errorKind")
    private final ErrorType errorType;
    /**
     * Specifies which message ID
     * has triggered the error
     */
    private final int referenceMessageId;
    /**
     * A message to the client
     * the currently occurring
     * Mistakes more accurately and describes
     * clarifies the reason for the occurrence.
     */
    @Nonnull
    private final String reason;

    public ErrorNotification(@Nonnull ErrorType errorType, int referenceMessageId, @Nonnull String reason) {
        super(MESSAGE_ID);
        this.errorType = errorType;
        this.referenceMessageId = referenceMessageId;
        this.reason = reason;
    }

    @Nonnull
    public ErrorType getErrorType() {
        return errorType;
    }

    public int getReferenceMessageId() {
        return referenceMessageId;
    }

    @Nonnull
    public String getReason() {
        return reason;
    }


}
