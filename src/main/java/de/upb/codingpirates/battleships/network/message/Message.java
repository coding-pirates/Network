package de.upb.codingpirates.battleships.network.message;

/**
 * Basic interface for all Messages
 *
 * @author Interdoc committee & Paul Becker
 */
public class Message {

    public final int messageId;

    protected Message(int messageId) {
        this.messageId = messageId;
    }

}
