package de.upb.codingpirates.battleships.network.message.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import de.upb.codingpirates.battleships.logic.*;
import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent by the server to the observer to give him the full score.
 *
 * @author Interdoc committee
 * @author Paul Becker
 * @author Andre Blanke
 */
@SuppressWarnings("unused")
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

    @Override
    public boolean equals(final Object object) {
        if (object == this)
            return true;
        if (object instanceof SpectatorGameStateResponse){
            SpectatorGameStateResponse message = (SpectatorGameStateResponse) obj;
            return state == message.state && shots.equals(message.shots) && ships.equals(message.ships) && players.equals(message.players);
        }
        return false;
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

    /**
     * @author Andre Blanke
     */
    public static final class Builder {

        @Nonnull
        private Collection<Client> players = new ArrayList<>();

        @Nonnull
        private Collection<Shot> shots = new ArrayList<>();
        @Nonnull
        private Map<Integer, Map<Integer, PlacementInfo>> ships = new HashMap<>();

        private GameState gameState;

        public SpectatorGameStateResponse build() {
            return new SpectatorGameStateResponse(players, shots, ships, gameState);
        }

        public Builder players(@Nonnull final Collection<Client> players) {
            this.players = players;
            return this;
        }

        public Builder shots(@Nonnull final Collection<Shot> shots) {
            this.shots = shots;
            return this;
        }

        public Builder ships(@Nonnull final Map<Integer, Map<Integer, PlacementInfo>> ships) {
            this.ships = ships;
            return this;
        }

        public Builder gameState(final GameState gameState) {
            this.gameState = gameState;
            return this;
        }
    }
}
