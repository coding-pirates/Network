package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Is sent from the server to the client to allow the client to successfully join as a player
 * to confirm.
 *
 * @author Interdoc committee & Paul Becker
 */
@SuppressWarnings("unused")
public class GameJoinPlayerResponse extends Message {

    public static final int MESSAGE_ID = 252;

    /**
     * Is the unique game ID of the
     * joined game
     */
    private final int gameId;

    public GameJoinPlayerResponse(int gameId) {
        super(MESSAGE_ID);
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }


}
