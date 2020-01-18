package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Can be sent from the client to the server to view all games of the current tournament
 * ask.
 */
public class TournamentGamesRequest extends Message {

    /**
     * Message id of {@link TournamentGamesRequest}
     */
    public static final int MESSAGE_ID = 403;

    TournamentGamesRequest() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof TournamentGamesRequest;
    }
}
