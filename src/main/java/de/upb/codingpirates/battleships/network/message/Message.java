package de.upb.codingpirates.battleships.network.message;

public abstract class Message {

    private int messageId;

    /* All subclasses should be declared in the "message" package. */
    Message() {
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(final int messageId) {
        this.messageId = messageId;
    }
}
