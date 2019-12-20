package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent by the player to the server to get his current score.
 *
 * @author Interdoc committee, Paul Becker
 */
public class PlayerGameStateRequest extends Message {

    /**
     * Message id of {@link PlayerGameStateRequest}
     */
    public static final int MESSAGE_ID = 305;

    PlayerGameStateRequest() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof PlayerGameStateRequest;
    }
}
