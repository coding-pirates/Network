package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.logic.ClientType;
import de.upb.codingpirates.battleships.logic.PlacementInfo;
import de.upb.codingpirates.battleships.logic.Shot;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;

public final class RequestBuilder {
    private RequestBuilder() {}

    @Nonnull
    private static final GameLeaveRequest gameLeaveRequest = new GameLeaveRequest();
    @Nonnull
    private static final LobbyRequest lobbyRequest = new LobbyRequest();
    @Nonnull
    private static final PlayerGameStateRequest playerGameStateRequest = new PlayerGameStateRequest();
    @Nonnull
    private static final PointsRequest pointsRequest = new PointsRequest();
    @Nonnull
    private static final RemainingTimeRequest remainingTimeRequest = new RemainingTimeRequest();
    @Nonnull
    private static final SpectatorGameStateRequest spectatorGameStateRequest = new SpectatorGameStateRequest();
    @Nonnull
    private static final TournamentGamesRequest tournamentGamesRequest = new TournamentGamesRequest();
    @Nonnull
    private static final TournamentParticipantsRequest tournamentParticipantsRequest = new TournamentParticipantsRequest();
    @Nonnull
    private static final TournamentPointsRequest tournamentPointsRequest = new TournamentPointsRequest();

    @Nonnull
    public static GameJoinPlayerRequest gameJoinPlayerRequest(int gameId){
        return new GameJoinPlayerRequest(gameId);
    }

    @Nonnull
    public static GameJoinSpectatorRequest gameJoinSpectatorRequest(int gameId){
        return new GameJoinSpectatorRequest(gameId);
    }

    @Nonnull
    public static GameLeaveRequest gameLeaveRequest(){
        return gameLeaveRequest;
    }

    @Nonnull
    public static LobbyRequest lobbyRequest(){
        return lobbyRequest;
    }

    @Nonnull
    public static PlaceShipsRequest placeShipsRequest(@Nonnull Map<Integer, PlacementInfo> positions){
        return new PlaceShipsRequest(positions);
    }

    @Nonnull
    public static PlayerGameStateRequest playerGameStateRequest(){
        return playerGameStateRequest;
    }

    @Nonnull
    public static PointsRequest pointsRequest(){
        return pointsRequest;
    }

    @Nonnull
    public static RemainingTimeRequest remainingTimeRequest(){
        return remainingTimeRequest;
    }

    @Nonnull
    public static ServerJoinRequest serverJoinRequest(@Nonnull String name, @Nonnull ClientType clientType){
        return new ServerJoinRequest(name, clientType);
    }

    @Nonnull
    public static ShotsRequest shotsRequest(@Nonnull Collection<Shot> shots){
        return new ShotsRequest(shots);
    }

    @Nonnull
    public static SpectatorGameStateRequest spectatorGameStateRequest(){
        return spectatorGameStateRequest;
    }

    @Nonnull
    public static TournamentGamesRequest tournamentGamesRequest(){
        return tournamentGamesRequest;
    }

    @Nonnull
    public static TournamentParticipantsRequest tournamentParticipantsRequest(){
        return tournamentParticipantsRequest;
    }

    @Nonnull
    public static TournamentPointsRequest tournamentPointsRequest(){
        return tournamentPointsRequest;
    }

}