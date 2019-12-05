package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent from the server to the client to allow the client to successfully join as an observer
 * to confirm.
 *
 * @author Interdoc committee & Paul Becker
 */
@SuppressWarnings("unused")
public class GameJoinSpectatorResponse extends Message {

    public static final int MESSAGE_ID = 253;

    /**
     * Is the unique game ID of the
     * joined game
     */
    private final int gameId;

    public GameJoinSpectatorResponse(int gameId) {
        super(MESSAGE_ID);
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

}
