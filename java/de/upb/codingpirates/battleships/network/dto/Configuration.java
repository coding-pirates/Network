package de.upb.codingpirates.battleships.network.dto;

import java.util.Collection;

public final class Configuration {

    private int maxPlayerCount;

    private int height;

    private int width;

    private int shotCount;

    private int hitPoints;

    private int sunkPoints;

    private long roundTime;

    private long visualizationTime;

    private Collection<Ship> ships;

    private int penaltyMinusPoints;

    private PenaltyKind penaltyKind;

    public int getMaxPlayerCount() {
        return maxPlayerCount;
    }

    public void setMaxPlayerCount(final int maxPlayerCount) {
        this.maxPlayerCount = maxPlayerCount;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getShotCount() {
        return shotCount;
    }

    public void setShotCount(final int shotCount) {
        this.shotCount = shotCount;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(final int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getSunkPoints() {
        return sunkPoints;
    }

    public void setSunkPoints(final int sunkPoints) {
        this.sunkPoints = sunkPoints;
    }

    public long getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(final long roundTime) {
        this.roundTime = roundTime;
    }

    public long getVisualizationTime() {
        return visualizationTime;
    }

    public void setVisualizationTime(final long visualizationTime) {
        this.visualizationTime = visualizationTime;
    }

    public Collection<Ship> getShips() {
        return ships;
    }

    public void setShips(final Collection<Ship> ships) {
        this.ships = ships;
    }

    public int getPenaltyMinusPoints() {
        return penaltyMinusPoints;
    }

    public void setPenaltyMinusPoints(final int penaltyMinusPoints) {
        this.penaltyMinusPoints = penaltyMinusPoints;
    }

    public PenaltyKind getPenaltyKind() {
        return penaltyKind;
    }

    public void setPenaltyKind(final PenaltyKind penaltyKind) {
        this.penaltyKind = penaltyKind;
    }
}
