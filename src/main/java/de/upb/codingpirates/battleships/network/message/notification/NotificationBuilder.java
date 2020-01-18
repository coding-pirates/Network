package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.logic.Client;
import de.upb.codingpirates.battleships.logic.Configuration;
import de.upb.codingpirates.battleships.logic.ErrorType;
import de.upb.codingpirates.battleships.logic.Shot;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;

public final class NotificationBuilder {
    private NotificationBuilder() {}

    @Nonnull
    private static final ContinueNotification continueNotification = new ContinueNotification();
    @Nonnull
    private static final GameStartNotification gameStartNotification = new GameStartNotification();
    @Nonnull
    private static final PauseNotification pauseNotification = new PauseNotification();
    @Nonnull
    private static final RoundStartNotification roundStartNotification = new RoundStartNotification();

    @Nonnull
    public static ContinueNotification continueNotification(){
        return continueNotification;
    }

    @Nonnull
    public static ErrorNotification errorNotification(@Nonnull ErrorType errorType, int referenceMessageId, @Nonnull String reason){
        return new ErrorNotification(errorType, referenceMessageId, reason);
    }

    @Nonnull
    public static FinishNotification finishNotification(@Nonnull Map<Integer, Integer> points, @Nonnull Collection<Integer> winner){
        return new FinishNotification(points, winner);
    }

    @Nonnull
    public static GameInitNotification gameInitNotification(@Nonnull Collection<Client> clientList, @Nonnull Configuration configuration){
        return new GameInitNotification(clientList, configuration);
    }

    @Nonnull
    public static GameStartNotification gameStartNotification(){
        return gameStartNotification;
    }

    @Nonnull
    public static LeaveNotification leaveNotification(int playerId){
        return new LeaveNotification(playerId);
    }

    @Nonnull
    public static PauseNotification pauseNotification(){
        return pauseNotification;
    }

    @Nonnull
    public static PlayerUpdateNotification playerUpdateNotification(@Nonnull Collection<Shot> hits, @Nonnull Map<Integer, Integer> points, @Nonnull Collection<Shot> sunk){
        return new PlayerUpdateNotification(hits, points, sunk);
    }

    @Nonnull
    public static RoundStartNotification roundStartNotification(){
        return roundStartNotification;
    }

    @Nonnull
    public static SpectatorUpdateNotification spectatorUpdateNotification(@Nonnull Collection<Shot> hits, @Nonnull Map<Integer, Integer> points, @Nonnull Collection<Shot> sunk, @Nonnull Collection<Shot> missed){
        return new SpectatorUpdateNotification(hits, points, sunk, missed);
    }

    @Nonnull
    public static TournamentFinishNotification tournamentFinishNotification(@Nonnull Collection<Integer> winner){
        return new TournamentFinishNotification(winner);
    }

}
