package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Reply to TournamentPointsRequest, which shows the total scores of all players of the
 * Tournament contains.
 *
 * @author Interdoc committee, Paul Becker
 */
public class TournamentPointsResponse extends Message {

    /**
     * Message id of {@link TournamentPointsResponse}
     */
    public static final int MESSAGE_ID = 451;

    /**
     * The total points for
     * all players of the tournament.
     */
    @Nonnull
    private Map<Integer, Integer> points;

    TournamentPointsResponse(@Nonnull Map<Integer, Integer> points) {
        super(MESSAGE_ID);
        this.points = points;
    }

    /**
     * @return {@link #points}
     */
    @Nonnull
    public Map<Integer, Integer> getPoints() {
        return points;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof TournamentPointsResponse){
            return points.equals(((TournamentPointsResponse)obj).points);
        }
        return false;
    }

}
