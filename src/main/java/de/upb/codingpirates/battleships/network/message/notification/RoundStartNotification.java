package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Will be sent from the server to all clients as soon as the next round begins.
 *
 * @author Interdoc committee, Paul Becker
 */
public class RoundStartNotification extends Message {


    /**
     * Message id of {@link RoundStartNotification}
     */
    public static final int MESSAGE_ID = 365;

    RoundStartNotification() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof RoundStartNotification;
    }
}
