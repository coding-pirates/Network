package de.upb.codingpirates.battleships.network.dto;

import java.util.Collection;

public final class Ship {

    private Collection<Point2D> positions;

    public Collection<Point2D> getPositions() {
        return positions;
    }

    public void setPositions(final Collection<Point2D> positions) {
        this.positions = positions;
    }
}
