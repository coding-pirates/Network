package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.*;

/**
 * Is sent from the server to the client to tell the client that now the game
 * starts.
 *
 * @author Interdoc committee & Paul Becker
 */
public class GameStartNotification extends Message {

    @SuppressWarnings("WeakerAccess")
    public static final int MESSAGE_ID = 363;

    public GameStartNotification() {
        super(MESSAGE_ID);
    }

}
