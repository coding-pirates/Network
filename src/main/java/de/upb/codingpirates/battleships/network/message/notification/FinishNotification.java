package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Defines the end of a game and contains the final results.
 *
 * @author Interdoc committee & Paul Becker
 */
@SuppressWarnings("unused")
public class FinishNotification extends Message {

    public static final int MESSAGE_ID = 364;

    /**
     * A map, which achieved the
     * Contains points of all players
     */
    @Nonnull
    private final Map<Integer, Integer> points;
    /**
     * The client ID of the winner.
     */
    private final int winner;

    public FinishNotification(@Nonnull Map<Integer, Integer> points, int winner) {
        super(MESSAGE_ID);
        this.points = points;
        this.winner = winner;
    }

    @Nonnull
    public Map<Integer, Integer> getPoints() {
        return points;
    }

    public int getWinner() {
        return winner;
    }
}
