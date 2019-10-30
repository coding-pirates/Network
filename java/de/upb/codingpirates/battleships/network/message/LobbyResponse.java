package de.upb.codingpirates.battleships.network.message;

import java.util.Collection;

import de.upb.codingpirates.battleships.network.dto.Game;

public final class LobbyResponse extends Message {

    private Collection<Game> games;

    public Collection<Game> getGames() {
        return games;
    }

    public void setGames(final Collection<Game> games) {
        this.games = games;
    }
}
