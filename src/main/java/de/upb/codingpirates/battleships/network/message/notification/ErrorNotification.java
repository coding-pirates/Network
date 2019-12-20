package de.upb.codingpirates.battleships.network.message.notification;

import com.google.gson.annotations.SerializedName;
import de.upb.codingpirates.battleships.logic.ErrorType;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;

/**
 * Defines a client-initiated error.
 *
 * @author Interdoc committee, Paul Becker
 */
public class ErrorNotification extends Message {

    /**
     * Message id of {@link ErrorNotification}
     */
    public static final int MESSAGE_ID = 999;

    /**
     * Specifies the error type
     */
    @Nonnull
    @SerializedName("errorKind")
    private final ErrorType errorType;
    /**
     * Specifies which message
     * has triggered the error
     */
    private final int referenceMessageId;
    /**
     * A message to the client describing
     * the currently occurring
     * mistakes more accurately and
     * clarifies the reason for the occurrence.
     */
    @Nonnull
    private final String reason;

    ErrorNotification(@Nonnull ErrorType errorType, int referenceMessageId, @Nonnull String reason) {
        super(MESSAGE_ID);
        this.errorType = errorType;
        this.referenceMessageId = referenceMessageId;
        this.reason = reason;
    }

    /**
     * @return {@link #errorType}
     */
    @Nonnull
    public ErrorType getErrorType() {
        return errorType;
    }

    /**
     * @return {@link #referenceMessageId}
     */
    public int getReferenceMessageId() {
        return referenceMessageId;
    }

    /**
     * @return {@link #reason}
     */
    @Nonnull
    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof ErrorNotification){
            return errorType.equals(((ErrorNotification)obj).errorType) && referenceMessageId ==((ErrorNotification)obj).referenceMessageId && reason.equals(((ErrorNotification)obj).reason);
        }
        return false;
    }
}
