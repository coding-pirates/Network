package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.logic.Shot;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;

/**
 * Sent by the server after each round to all observers, to those who made it
 * To announce shots and new points.
 *
 * @author Interdoc committee, Paul Becker
 */
@SuppressWarnings("unused")
public class SpectatorUpdateNotification extends Message {

    /**
     * Message id of {@link SpectatorUpdateNotification}
     */
    public static final int MESSAGE_ID = 368;

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
    /**
     * A collection of all shots,
     * that did not hit a ship
     */
    @Nonnull
    private final Collection<Shot> missed;

    SpectatorUpdateNotification(@Nonnull Collection<Shot> hits, @Nonnull Map<Integer, Integer> points, @Nonnull Collection<Shot> sunk, @Nonnull Collection<Shot> missed) {
        super(MESSAGE_ID);
        this.hits = hits;
        this.points = points;
        this.sunk = sunk;
        this.missed = missed;
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

    /**
     * @return {@link #missed}
     */
    @Nonnull
    public Collection<Shot> getMissed() {
        return missed;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof SpectatorUpdateNotification){
            return hits.equals(((SpectatorUpdateNotification)obj).hits) && points.equals(((SpectatorUpdateNotification)obj).points) && missed.equals(((SpectatorUpdateNotification)obj).missed) && sunk.equals(((SpectatorUpdateNotification)obj).sunk);
        }
        return false;
    }
}
