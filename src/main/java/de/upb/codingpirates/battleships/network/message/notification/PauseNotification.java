package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent by the server to all clients when the host pauses the game.
 *
 * @author Interdoc committee, Paul Becker
 */
public class PauseNotification extends Message {

    /**
     * Message id of {@link PauseNotification}
     */
    public static final int MESSAGE_ID = 361;

    PauseNotification() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof PauseNotification;
    }
}
