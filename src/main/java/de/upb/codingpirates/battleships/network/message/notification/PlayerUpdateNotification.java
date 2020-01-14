package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.logic.Shot;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;

/**
 * It is sent by the server after each round to all active participants to give them the
 * announced shots and new points.
 *
 * @author Interdoc committee, Paul Becker
 */
public class PlayerUpdateNotification extends Message {

    /**
     * Message id of {@link PlayerUpdateNotification}
     */
    public static final int MESSAGE_ID = 369;

    /**
     * A collection about shots that
     * hit a ship in one turn
     * have (including those in sunk)
     */
    @Nonnull
    private final Collection<Shot> hits;
    /**
     * A map showing the new scores
     * contains all the players who
     * Points in the current round
     * got.
     */
    @Nonnull
    private final Map<Integer, Integer> points;
    /**
     * A collection of all shots,
     * the sinking of a ship
     * have led
     */
    @Nonnull
    private final Collection<Shot> sunk;

    public PlayerUpdateNotification(@Nonnull Collection<Shot> hits, @Nonnull Map<Integer, Integer> points, @Nonnull Collection<Shot> sunk) {
        super(MESSAGE_ID);
        this.hits = hits;
        this.points = points;
        this.sunk = sunk;
    }

    /**
     * @return {@link #hits}
     */
    @Nonnull
    public Collection<Shot> getHits() {
        return hits;
    }

    /**
     * @return {@link #points}
     */
    @Nonnull
    public Map<Integer, Integer> getPoints() {
        return points;
    }

    /**
     * @return {@link #sunk}
     */
    @Nonnull
    public Collection<Shot> getSunk() {
        return sunk;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof PlayerUpdateNotification){
            return hits.equals(((PlayerUpdateNotification)obj).hits) && points.equals(((PlayerUpdateNotification)obj).points) && sunk.equals(((PlayerUpdateNotification)obj).sunk);
        }
        return false;
    }
}
