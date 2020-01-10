package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.*;

/**
 * Is sent from the server to the client to tell the client that now the game
 * starts.
 *
 * @author Interdoc committee, Paul Becker
 */
public class GameStartNotification extends Message {

    /**
     * Message id of {@link GameStartNotification}
     */
    public static final int MESSAGE_ID = 363;

    GameStartNotification() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof GameStartNotification;
    }
}
