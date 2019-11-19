package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent from the client to the server to join a game as a teammate.
 *
 * @author Interdoc committee & Paul Becker
 */
public class GameJoinPlayerRequest extends Message {

    public static final int MESSAGE_ID = 202;

    /**
     * Is the unique game ID of the
     * Game the client will join
     * would like to
     */
    private final int gameId;

    public GameJoinPlayerRequest(int gameId) {
        super(MESSAGE_ID);
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

}
