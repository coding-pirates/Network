package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Dispatched by the server to all clients when a player no longer participates in the game.
 * See also details on leaving a game
 *
 * @author Interdoc committee, Paul Becker
 */
public class LeaveNotification extends Message {

    /**
     * Message id of {@link LeaveNotification}
     */
    public static final int MESSAGE_ID = 367;

    /**
     * The ID of the client which
     * has left or was kicked
     */
    private final int playerId;

    LeaveNotification(int playerId) {
        super(MESSAGE_ID);
        this.playerId = playerId;
    }

    /**
     * @return {@link #playerId}
     */
    public int getPlayerId() {
        return playerId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof LeaveNotification){
            return playerId == ((LeaveNotification)obj).playerId;
        }
        return false;
    }
}
