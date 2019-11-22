package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.logic.Client;
import de.upb.codingpirates.battleships.logic.GameState;
import de.upb.codingpirates.battleships.logic.PlacementInfo;
import de.upb.codingpirates.battleships.logic.Shot;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;

/**
 * Sent by the server to the observer to give him the full score.
 *
 * @author Interdoc committee & Paul Becker
 */
public class SpectatorGameStateResponse extends Message {

    public static final int MESSAGE_ID = 356;

    /**
     * A collection of clients,
     * who currently participate in the game
     */
    @Nonnull
    private final Collection<Client> players;
    /**
     * A collection of all shots,
     * which has been submitted in the game so far
     * were
     */
    @Nonnull
    private final Collection<Shot> shots;
    /**
     * An overview of all placed
     * Ships for each player: A map
     * from client IDs to a map of
     * Ship IDs on position info
     */
    @Nonnull
    private final Map<Integer, Map<Integer, PlacementInfo>> ships;
    /**
     * Contains the status of the game
     */
    @Nonnull
    private final GameState state;

    public SpectatorGameStateResponse(@Nonnull Collection<Client> players, @Nonnull Collection<Shot> shots, @Nonnull Map<Integer, Map<Integer, PlacementInfo>> ships, @Nonnull GameState state) {
        super(MESSAGE_ID);
        this.players = players;
        this.shots = shots;
        this.ships = ships;
        this.state = state;
    }

    @Nonnull
    public Collection<Client> getPlayers() {
        return players;
    }

    @Nonnull
    public Collection<Shot> getShots() {
        return shots;
    }

    @Nonnull
    public Map<Integer, Map<Integer, PlacementInfo>> getShips() {
        return ships;
    }

    @Nonnull
    public GameState getState() {
        return state;
    }

}
