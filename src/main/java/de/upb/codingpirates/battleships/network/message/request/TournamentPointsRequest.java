package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Can be sent from the client to the server to get the total score of the current
 * Tournament.
 *
 * @author Interdoc committee, Paul Becker
 */
public class TournamentPointsRequest extends Message {

    /**
     * Message id of {@link TournamentPointsRequest}
     */
    public static final int MESSAGE_ID = 401;

    TournamentPointsRequest() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof TournamentPointsRequest;
    }
}
