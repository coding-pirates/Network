package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent from the server to the client to allow the client to successfully join as an observer
 * to confirm.
 *
 * @author Interdoc committee, Paul Becker
 */
public class GameJoinSpectatorResponse extends Message {

    /**
     * Message id of {@link GameJoinSpectatorResponse}
     */
    public static final int MESSAGE_ID = 253;

    /**
     * Is the unique game ID of the
     * joined game
     */
    private final int gameId;

    GameJoinSpectatorResponse(int gameId) {
        super(MESSAGE_ID);
        this.gameId = gameId;
    }

    /**
     * @return {@linkplain #gameId}
     */
    public int getGameId() {
        return gameId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof GameJoinSpectatorResponse){
            return gameId == ((GameJoinSpectatorResponse)obj).gameId;
        }
        return false;
    }

}
