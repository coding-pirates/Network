package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Is sent from the server to the client to allow the client to successfully join as a player
 * to confirm.
 *
 * @author Interdoc committee, Paul Becker
 */
public class GameJoinPlayerResponse extends Message {

    /**
     * Message id of {@link GameJoinPlayerResponse}
     */
    public static final int MESSAGE_ID = 252;

    /**
     * Is the unique game ID of the
     * joined game
     */
    private final int gameId;

    GameJoinPlayerResponse(int gameId) {
        super(MESSAGE_ID);
        this.gameId = gameId;
    }

    /**
     * @return {@link #gameId}
     */
    public int getGameId() {
        return gameId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof GameJoinPlayerResponse){
            return gameId == ((GameJoinPlayerResponse)obj).gameId;
        }
        return false;
    }

}
