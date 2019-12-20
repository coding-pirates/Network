package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Defines the end of the tournament and contains the winners.
 */
public class TournamentFinishNotification extends Message {

    /**
     * Message id of {@link TournamentFinishNotification}
     */
    public static final int MESSAGE_ID = 461;

    /**
     * The client ID's of the winners.
     */
    @Nonnull
    private Collection<Integer> winner;

    TournamentFinishNotification(@Nonnull Collection<Integer> winner) {
        super(MESSAGE_ID);
        this.winner = winner;
    }

    /**
     * @return {@link #winner}
     */
    @Nonnull
    public Collection<Integer> getWinner() {
        return winner;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof TournamentFinishNotification){
            return winner.equals(((TournamentFinishNotification)obj).winner);
        }
        return false;
    }
}
