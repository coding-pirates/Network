package de.upb.codingpirates.battleships.network.message.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import de.upb.codingpirates.battleships.logic.Client;
import de.upb.codingpirates.battleships.logic.GameState;
import de.upb.codingpirates.battleships.logic.PlacementInfo;
import de.upb.codingpirates.battleships.logic.Shot;
import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent by the server to the player to show him his current score.
 *
 * @author Interdoc committee
 * @author Paul Becker
 * @author Andre Blanke
 */
public class PlayerGameStateResponse extends Message {

    public static final int MESSAGE_ID = 355;

    /**
     * Contains the status of the game
     */
    @Nonnull
    private final GameState state;
    /**
     * A collection about shots that
     * in all previous rounds
     * Have met ship (including sunken)
     */
    @Nonnull
    private final Collection<Shot> hits;
    /**
     * A collection of all shots,
     * in all previous rounds
     * led to the sinking of a ship
     * to have
     */
    @Nonnull
    private final Collection<Shot> sunk;
    /**
     * An overview of all of the player
     * placed ships: A map
     * from ship IDs to position info
     */
    @Nonnull
    private final Map<Integer, PlacementInfo> ships;
    /**
     * A collection of clients,
     * who currently participate in the game
     */
    @Nonnull
    private final Collection<Client> players;

    public PlayerGameStateResponse(@Nonnull GameState state, @Nonnull Collection<Shot> hits, @Nonnull Collection<Shot> sunk, @Nonnull Map<Integer, PlacementInfo> ships, @Nonnull Collection<Client> player) {
        super(MESSAGE_ID);
        this.state   = state;
        this.hits    = hits;
        this.sunk    = sunk;
        this.ships   = ships;
        this.players = player;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this)
            return true;
        if (object instanceof PlayerGameStateResponse){
            PlayerGameStateResponse message = (PlayerGameStateResponse) obj;
            return state == message.state && hits.equals(message.hits) && sunk.equals(message.sunk) && ships.equals(message.ships) && players.equals(message.players);
        }
        return false;
    }

    @Nonnull
    @SuppressWarnings("unused")
    public GameState getState() {
        return state;
    }


    @Nonnull
    @SuppressWarnings("unused")
    public Collection<Shot> getHits() {
        return hits;
    }


    @Nonnull
    @SuppressWarnings("unused")
    public Collection<Shot> getSunk() {
        return sunk;
    }


    @Nonnull
    public Map<Integer, PlacementInfo> getShips() {
        return ships;
    }

    @Nonnull
    public Collection<Client> getPlayer() {
        return players;
    }

    /**
     * @author Andre Blanke
     */
    public static final class Builder {

        private GameState gameState;

        @Nonnull
        private Collection<Shot> hits = new ArrayList<>();

        @Nonnull
        private Collection<Shot> sunk = new ArrayList<>();

        @Nonnull
        private Map<Integer, PlacementInfo> ships = new HashMap<>();

        @Nonnull
        private Collection<Client> players = new ArrayList<>();

        public PlayerGameStateResponse build() {
            return new PlayerGameStateResponse(gameState, hits, sunk, ships, players);
        }

        public Builder gameState(@Nonnull final GameState gameState) {
            this.gameState = gameState;
            return this;
        }

        public Builder hits(@Nonnull final Collection<Shot> hits) {
            this.hits = hits;
            return this;
        }

        public Builder sunk(@Nonnull final Collection<Shot> sunk) {
            this.sunk = sunk;
            return this;
        }

        public Builder ships(@Nonnull final Map<Integer, PlacementInfo> ships) {
            this.ships = ships;
            return this;
        }

        public Builder players(@Nonnull final Collection<Client> players) {
            this.players = players;
            return this;
        }
    }
}
