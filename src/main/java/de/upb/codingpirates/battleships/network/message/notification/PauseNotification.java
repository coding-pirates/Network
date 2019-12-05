package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent by the server to all clients when the host pauses the game.
 *
 * @author Interdoc committee & Paul Becker
 */
public class PauseNotification extends Message {


    @SuppressWarnings("WeakerAccess")
    public static final int MESSAGE_ID = 361;

    public PauseNotification() {
        super(MESSAGE_ID);
    }


}
