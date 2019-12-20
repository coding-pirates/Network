package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.logic.*;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;

public final class ResponseBuilder {
    private ResponseBuilder() {}

    @Nonnull
    private static final PlaceShipsResponse placeShipsResponse = new PlaceShipsResponse();
    @Nonnull
    private static final GameLeaveResponse gameLeaveResponse = new GameLeaveResponse();

    @Nonnull
    public static GameJoinPlayerResponse gameJoinPlayerResponse(int gameId){
        return new GameJoinPlayerResponse(gameId);
    }

    @Nonnull
    public static GameJoinSpectatorResponse gameJoinSpectatorResponse(int gameId){
        return new GameJoinSpectatorResponse(gameId);
    }

    @Nonnull
    public static GameLeaveResponse gameLeaveResponse(){
        return gameLeaveResponse;
    }

    @Nonnull
    public static PlaceShipsResponse placeShipsResponse(){
        return placeShipsResponse;
    }

    @Nonnull
    public static PlayerGameStateResponse playerGameStateResponse(@Nonnull GameState state, @Nonnull Collection<Shot> hits, @Nonnull Collection<Shot> sunk, @Nonnull Map<Integer, PlacementInfo> ships, @Nonnull Collection<Client> player){
        return new PlayerGameStateResponse(state,hits,sunk,ships,player);
    }

    @Nonnull
    public static PointsResponse pointsResponse(@Nonnull Map<Integer, Integer> points){
        return new PointsResponse(points);
    }

    @Nonnull
    public static RemainingTimeResponse remainingTimeResponse(long time){
        return new RemainingTimeResponse(time);
    }

    @Nonnull
    public static ServerJoinResponse serverJoinResponse(int clientId){
        return new ServerJoinResponse(clientId);
    }

    @Nonnull
    public static ShotsResponse shotsResponse(){
        return new ShotsResponse();
    }

    @Nonnull
    public static SpectatorGameStateResponse spectatorGameStateResponse(@Nonnull Collection<Client> players, @Nonnull Collection<Shot> shots, @Nonnull Map<Integer, Map<Integer, PlacementInfo>> ships, @Nonnull GameState state){
        return new SpectatorGameStateResponse(players, shots, ships, state);
    }

    @Nonnull
    public static TournamentGamesResponse tournamentGamesResponse(@Nonnull Collection<Game> games){
        return new TournamentGamesResponse(games);
    }

    @Nonnull
    public static TournamentParticipantsResponse tournamentParticipantsResponse(boolean participates){
        return new TournamentParticipantsResponse(participates);
    }

    @Nonnull
    public static TournamentPointsResponse tournamentPointsResponse(@Nonnull Map<Integer, Integer> points){
        return new TournamentPointsResponse(points);
    }

//-----------------------------------------------return message builder-------------------------------------------------

    @Nonnull
    public static PlayerGameStateResponse.Builder playerGameStateResponse(){
        return new PlayerGameStateResponse.Builder();
    }

    @Nonnull
    public static SpectatorGameStateResponse.Builder spectatorGameStateResponse(){
        return new SpectatorGameStateResponse.Builder();
    }
}
