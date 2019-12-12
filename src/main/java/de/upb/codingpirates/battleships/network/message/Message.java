package de.upb.codingpirates.battleships.network.message;

/**
 * Basic interface for all Messages
 *
 * @author Interdoc committee & Paul Becker
 */
public class Message {

    private final int messageId;

    protected Message(final int messageId) {
        this.messageId = messageId;
    }

    public int getMessageId() {
        return messageId;
    }
}
