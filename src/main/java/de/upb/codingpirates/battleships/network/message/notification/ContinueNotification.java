package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Dispatched by the server to all clients when the organizer resumes the game.
 *
 * @author Interdoc committee & Paul Becker
 */
public class ContinueNotification extends Message {

    @SuppressWarnings("WeakerAccess")
    public static final int MESSAGE_ID = 362;

    public ContinueNotification() {
        super(MESSAGE_ID);
    }

}
