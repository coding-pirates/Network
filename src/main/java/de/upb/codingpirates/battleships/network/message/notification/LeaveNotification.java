package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Dispatched by the server to all clients when a player no longer participates in the game.
 * See also details on leaving a game
 *
 * @author Interdoc committee & Paul Becker
 */
@SuppressWarnings("unused")
public class LeaveNotification extends Message {

    public static final int MESSAGE_ID = 367;

    /**
     * The ID of the client who is the game
     * has left or was kicked
     */
    private final int playerId;

    public LeaveNotification(Integer playerId) {
        super(MESSAGE_ID);
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

}
