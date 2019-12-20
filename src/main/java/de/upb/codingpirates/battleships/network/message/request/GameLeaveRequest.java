package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent from the client to the server to exit the current game
 */
public class GameLeaveRequest extends Message {

    /**
     * Message id of {@link GameLeaveRequest}
     */
    public static final int MESSAGE_ID = 307;

    GameLeaveRequest() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof GameLeaveRequest;
    }
}
