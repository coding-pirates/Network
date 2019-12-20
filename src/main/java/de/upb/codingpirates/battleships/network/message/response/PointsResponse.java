package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Map;


/**
 * Reply to PointsRequest, which contains the complete points list.
 *
 * @author Interdoc committee, Paul Becker
 */
public class PointsResponse extends Message {

    /**
     * Message id of {@link PointsResponse}
     */
    public static final int MESSAGE_ID = 353;

    /**
     * A map that is the absolute
     * Contains scores of all players
     */
    @Nonnull
    private final Map<Integer, Integer> points;

    PointsResponse(@Nonnull Map<Integer, Integer> points) {
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
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof PointsResponse){
            return points.equals(((PointsResponse)obj).points);
        }
        return false;
    }

}
