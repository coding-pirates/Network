package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent by the observer to the server to get the full score.
 *
 * @author Interdoc committee, Paul Becker
 */
public class SpectatorGameStateRequest extends Message {

    /**
     * Message id of {@link SpectatorGameStateRequest}
     */
    public static final int MESSAGE_ID = 306;

    SpectatorGameStateRequest() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof SpectatorGameStateRequest;
    }
}
