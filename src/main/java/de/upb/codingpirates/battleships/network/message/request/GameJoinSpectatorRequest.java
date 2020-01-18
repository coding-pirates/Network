package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent from the client to the server to join a game as an observer.
 *
 * @author Interdoc committee, Paul Becker
 */
public class GameJoinSpectatorRequest extends Message {

    /**
     * Message id of {@link GameJoinSpectatorRequest}
     */
    public static final int MESSAGE_ID = 203;

    /**
     * Is the unique game ID of the
     * Game the client will join
     * would like to
     */
    private final int gameId;

    GameJoinSpectatorRequest(int gameId) {
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
        if(obj instanceof GameJoinSpectatorRequest){
            return gameId == ((GameJoinSpectatorRequest)obj).gameId ;
        }
        return false;
    }

}
