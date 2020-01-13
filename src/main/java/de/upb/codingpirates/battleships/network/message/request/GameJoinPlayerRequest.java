package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent from the client to the server to join a game as a teammate.
 *
 * @author Interdoc committee, Paul Becker
 */
public class GameJoinPlayerRequest extends Message {

    /**
     * Message id of {@link GameJoinPlayerRequest}
     */
    public static final int MESSAGE_ID = 202;

    /**
     * Is the unique game ID of the
     * Game the client will join
     * would like to
     */
    private final int gameId;

    GameJoinPlayerRequest(int gameId) {
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
        if(obj instanceof GameJoinPlayerRequest){
            return gameId == ((GameJoinPlayerRequest)obj).gameId ;
        }
        return false;
    }

}
