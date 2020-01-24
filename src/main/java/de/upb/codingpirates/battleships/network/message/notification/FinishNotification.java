package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;

/**
 * Defines the end of a game and contains the final results.
 *
 * @author Interdoc committee, Paul Becker
 */
public class FinishNotification extends Message {

    /**
     * Message id of {@link FinishNotification}
     */
    public static final int MESSAGE_ID = 364;

    /**
     * A map, which achieved the
     * Contains points of all players
     */
    @Nonnull
    private final Map<Integer, Integer> points;

    /**
     * The client ID's of the winners.
     */
    @Nonnull
    private final Collection<Integer> winner;

    FinishNotification(@Nonnull Map<Integer, Integer> points, @Nonnull Collection<Integer> winner) {
        super(MESSAGE_ID);
        this.points = points;
        this.winner = winner;
    }

    /**
     * @return {@link #points}
     */
    @Nonnull
    public Map<Integer, Integer> getPoints() {
        return this.points;
    }

    /**
     * @return {@link #winner}
     */
    @Nonnull
    public Collection<Integer> getWinner() {
        return this.winner;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof FinishNotification){
            return points.equals(((FinishNotification)obj).points) && winner.equals(((FinishNotification)obj).winner);
        }
        return false;
    }
}
