package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Reply to GameLeaveRequest to confirm quitting the game.
 */
public class GameLeaveResponse extends Message {

    /**
     * Message id of {@link GameLeaveResponse}
     */
    public static final int MESSAGE_ID = 357;

    GameLeaveResponse() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof GameLeaveResponse;
    }
}
