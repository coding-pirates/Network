package de.upb.codingpirates.battleships.network.message;

/**
 * Basic interface for all Messages
 *
 * @author Interdoc committee, Paul Becker
 */
public abstract class Message {

    private final int messageId;

    protected Message(final int messageId) {
        this.messageId = messageId;
    }

    /**
     * @return the messageId
     */
    public int getMessageId() {
        return messageId;
    }
}
