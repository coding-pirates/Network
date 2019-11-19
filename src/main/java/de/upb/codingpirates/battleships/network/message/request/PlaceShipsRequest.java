package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.logic.util.PlacementInfo;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Sent from the client to the server to tell the server the ship positions.
 *
 * @author Interdoc committee & Paul Becker
 */
public class PlaceShipsRequest extends Message {

    public static final int MESSAGE_ID = 301;

    /**
     * A map that ships with all
     * Ship ID and PlacmentInfo
     * (Position and rotation)
     * contains
     */
    @Nonnull
    private final Map<Integer, PlacementInfo> positions;

    public PlaceShipsRequest(@Nonnull Map<Integer, PlacementInfo> positions) {
        super(MESSAGE_ID);
        this.positions = positions;
    }

    @Nonnull
    public Map<Integer, PlacementInfo> getPositions() {
        return positions;
    }

}