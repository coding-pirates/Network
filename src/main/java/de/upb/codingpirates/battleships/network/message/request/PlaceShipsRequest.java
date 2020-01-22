package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.logic.PlacementInfo;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Sent from the client to the server to tell the server the ship positions.
 *
 * @author Interdoc committee, Paul Becker
 */
public class PlaceShipsRequest extends Message {

    /**
     * Message id of {@link PlaceShipsRequest}
     */
    public static final int MESSAGE_ID = 301;

    /**
     * A map that ships with all
     * Ship ID and PlacementInfo
     * (Position and rotation)
     * contains
     */
    @Nonnull
    private final Map<Integer, PlacementInfo> positions;

    PlaceShipsRequest(@Nonnull Map<Integer, PlacementInfo> positions) {
        super(MESSAGE_ID);
        this.positions = positions;
    }

    /**
     * @return {@link #positions}
     */
    @Nonnull
    public Map<Integer, PlacementInfo> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof PlaceShipsRequest) {
            return positions.equals(((PlaceShipsRequest) obj).positions);
        }
        return false;
    }

    @Override
    public String toString() {
        return "PlaceShipsRequest{" +
                "positions=" + positions +
                '}';
    }
}
