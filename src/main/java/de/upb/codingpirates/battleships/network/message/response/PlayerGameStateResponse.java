package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.logic.Client;
import de.upb.codingpirates.battleships.logic.PlacementInfo;
import de.upb.codingpirates.battleships.logic.Shot;
import de.upb.codingpirates.battleships.logic.GameState;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;

/**
 * Sent by the server to the player to show him his current score.
 *
 * @author Interdoc committee & Paul Becker
 */
@SuppressWarnings("unused")
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
        this.state = state;
        this.hits = hits;
        this.sunk = sunk;
        this.ships = ships;
        this.players = player;
    }

    @Nonnull
    public GameState getState() {
        return state;
    }


    @Nonnull
    public Collection<Shot> getHits() {
        return hits;
    }


    @Nonnull
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

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof PlayerGameStateResponse){
            PlayerGameStateResponse message = (PlayerGameStateResponse) obj;
            return state == message.state && hits.equals(message.hits) && sunk.equals(message.sunk) && ships.equals(message.ships) && players.equals(message.players);
        }
        return false;
    }

}
