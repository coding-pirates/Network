package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Dispatched by the server to all clients when the organizer resumes the game.
 *
 * @author Interdoc committee, Paul Becker
 */
public class ContinueNotification extends Message {

    /**
     * Message id of {@link ContinueNotification}
     */
    public static final int MESSAGE_ID = 362;

    ContinueNotification() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof ContinueNotification;
    }
}
