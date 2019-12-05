package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent from the client to the server to join a game as an observer.
 *
 * @author Interdoc committee & Paul Becker
 */
public class GameJoinSpectatorRequest extends Message {

    @SuppressWarnings("WeakerAccess")
    public static final int MESSAGE_ID = 203;

    /**
     * Is the unique game ID of the
     * Game the client will join
     * would like to
     */
    private final int gameId;

    public GameJoinSpectatorRequest(int gameId) {
        super(MESSAGE_ID);
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

}
