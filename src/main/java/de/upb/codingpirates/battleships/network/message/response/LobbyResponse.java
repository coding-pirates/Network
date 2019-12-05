package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.logic.Game;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Is the answer of the server on a LobbyRequest and contains all currently existing games
 * of the server.
 *
 * @author Interdoc committee & Paul Becker
 */
public class LobbyResponse extends Message {

    @SuppressWarnings("WeakerAccess")
    public static final int MESSAGE_ID = 251;

    /**
     * A collection, the game
     * Objects of all past, current
     * and planned games
     */
    @Nonnull
    private final Collection<Game> games;

    public LobbyResponse(@Nonnull Collection<Game> games) {
        super(MESSAGE_ID);
        this.games = games;
    }

    @Nonnull
    public Collection<Game> getGames() {
        return games;
    }

}
