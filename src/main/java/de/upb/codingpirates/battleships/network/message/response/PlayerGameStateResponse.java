package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.logic.Client;
import de.upb.codingpirates.battleships.logic.PlacementInfo;
import de.upb.codingpirates.battleships.logic.Shot;
import de.upb.codingpirates.battleships.logic.GameState;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Sent by the server to the player to show him his current score.
 *
 * @author Interdoc committee
 * @author Paul Becker
 * @author Andre Blanke
 */
public class PlayerGameStateResponse extends Message {

    /**
     * Message id of {@link PlayerGameStateResponse}
     */
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

    PlayerGameStateResponse(@Nonnull GameState state, @Nonnull Collection<Shot> hits, @Nonnull Collection<Shot> sunk, @Nonnull Map<Integer, PlacementInfo> ships, @Nonnull Collection<Client> player) {
        super(MESSAGE_ID);
        this.state   = state;
        this.hits    = hits;
        this.sunk    = sunk;
        this.ships   = ships;
        this.players = player;
    }

    /**
     * @return {@link #state}
     */
    @Nonnull
    public GameState getState() {
        return state;
    }

    /**
     * @return {@link #hits}
     */
    @Nonnull
    public Collection<Shot> getHits() {
        return hits;
    }

    /**
     * @return {@link #sunk}
     */
    @Nonnull
    public Collection<Shot> getSunk() {
        return sunk;
    }

    /**
     * @return {@link #ships}
     */
    @Nonnull
    public Map<Integer, PlacementInfo> getShips() {
        return ships;
    }

    /**
     * @return {@link #players}
     */
    @Nonnull
    public Collection<Client> getPlayer() {
        return players;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this)
            return true;
        if (object instanceof PlayerGameStateResponse){
            PlayerGameStateResponse message = (PlayerGameStateResponse) object;
            return state == message.state && hits.equals(message.hits) && sunk.equals(message.sunk) && ships.equals(message.ships) && players.equals(message.players);
        }
        return false;
    }

    /**
     * @author Andre Blanke
     */
    public static final class Builder {

        private GameState gameState;

        /**
         * {@link PlayerGameStateResponse#hits}
         */
        @Nonnull
        private Collection<Shot> hits = new ArrayList<>();

        /**
         * {@link PlayerGameStateResponse#sunk}
         */
        @Nonnull
        private Collection<Shot> sunk = new ArrayList<>();

        /**
         * {@link PlayerGameStateResponse#ships}
         */
        @Nonnull
        private Map<Integer, PlacementInfo> ships = new HashMap<>();

        /**
         * {@link PlayerGameStateResponse#players}
         */
        @Nonnull
        private Collection<Client> players = new ArrayList<>();

        /**
         * @return creates new instance of {@link PlayerGameStateResponse} from {@link #gameState}, {@link #hits}, {@link #sunk}, {@link #ships}, {@link #players}
         */
        public PlayerGameStateResponse build() {
            return new PlayerGameStateResponse(gameState, hits, sunk, ships, players);
        }

        /**
         * sets {@link #gameState}
         *
         * @param gameState new gameState
         * @return this PlayerGameStateResponse builder
         */
        public Builder gameState(@Nonnull final GameState gameState) {
            this.gameState = gameState;
            return this;
        }

        /**
         * sets {@link #hits}
         * @param hits new hits
         * @return this PlayerGameStateResponse builder
         */
        public Builder hits(@Nonnull final Collection<Shot> hits) {
            this.hits = hits;
            return this;
        }

        /**
         * sets {@link #sunk}
         * @param sunk new sunk
         * @return this PlayerGameStateResponse builder
         */
        public Builder sunk(@Nonnull final Collection<Shot> sunk) {
            this.sunk = sunk;
            return this;
        }

        /**
         * sets {@link #ships}
         * @param ships new ships
         * @return this PlayerGameStateResponse builder
         */
        public Builder ships(@Nonnull final Map<Integer, PlacementInfo> ships) {
            this.ships = ships;
            return this;
        }

        /**
         * sets {@link #players}
         * @param players new players
         * @return this PlayerGameStateResponse builder
         */
        public Builder players(@Nonnull final Collection<Client> players) {
            this.players = players;
            return this;
        }
    }
}
