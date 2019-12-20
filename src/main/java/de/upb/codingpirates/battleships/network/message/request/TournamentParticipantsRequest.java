package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Can be sent from the client to the server to check whether it is currently on a
 * Participates in the tournament.
 */
public class TournamentParticipantsRequest extends Message {

    /**
     * Message id of {@link TournamentParticipantsRequest}
     */
    public static final int MESSAGE_ID = 402;

    TournamentParticipantsRequest() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof TournamentParticipantsRequest;
    }
}
