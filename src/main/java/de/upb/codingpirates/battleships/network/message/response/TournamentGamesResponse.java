package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.logic.Game;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Reply to TournamentGamesRequest, which contains the games of the tournament.
 */
public class TournamentGamesResponse extends Message {

    /**
     * Message id of {@link TournamentGamesResponse}
     */
    public static final int MESSAGE_ID = 453;

    /**
     * The games of the tournament
     */
    @Nonnull
    private Collection<Game> games;

    TournamentGamesResponse(@Nonnull Collection<Game> games) {
        super(MESSAGE_ID);
        this.games = games;
    }

    /**
     * @return {@link #games}
     */
    @Nonnull
    public Collection<Game> getGames() {
        return games;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof TournamentGamesResponse){
            return games.equals(((TournamentGamesResponse)obj).games);
        }
        return false;
    }

}
