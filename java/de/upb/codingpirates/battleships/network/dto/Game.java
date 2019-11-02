package de.upb.codingpirates.battleships.network.dto;

public final class Game {

    private String name;

    private int id;

    private int currentPlayerCount;

    private GameState state;

    private Configuration config;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getCurrentPlayerCount() {
        return currentPlayerCount;
    }

    public void setCurrentPlayerCount(final int currentPlayerCount) {
        this.currentPlayerCount = currentPlayerCount;
    }

    public GameState getState() {
        return state;
    }

    public void setState(final GameState state) {
        this.state = state;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(final Configuration config) {
        this.config = config;
    }
}
