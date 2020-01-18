package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Reply to TournamentParticipatesRequest, which indicates whether the client is currently on a
 * Participates in the tournament.
 */
public class TournamentParticipantsResponse extends Message {

    /**
     * Message id of {@link TournamentParticipantsResponse}
     */
    public static final int MESSAGE_ID = 452;

    /**
     * Indicates whether the client is current
     * participates in a tournament.
     */
    private boolean participates;

    TournamentParticipantsResponse(boolean participates) {
        super(MESSAGE_ID);
        this.participates = participates;
    }

    /**
     * @return {@link #participates}
     */
    public boolean isParticipating() {
        return participates;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof TournamentParticipantsResponse){
            return participates == ((TournamentParticipantsResponse)obj).participates;
        }
        return false;
    }

}
