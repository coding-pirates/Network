package de.upb.codingpirates.battleships.network.test;

import com.google.common.collect.Lists;
import de.upb.codingpirates.battleships.logic.*;
import de.upb.codingpirates.battleships.network.exceptions.parser.ParserException;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.Parser;
import de.upb.codingpirates.battleships.network.message.notification.NotificationBuilder;
import de.upb.codingpirates.battleships.network.message.request.RequestBuilder;
import de.upb.codingpirates.battleships.network.message.request.ServerJoinRequest;
import de.upb.codingpirates.battleships.network.message.response.ResponseBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ParserTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random RNG = new Random();

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    void parser() throws ParserException {
        Parser parser = new Parser();
        String test = "{\"games\":[{\"name\":\"testomat\",\"id\":0,\"currentPlayerCount\":5,\"state\":\"LOBBY\",\"config\":{\"maxPlayerCount\":0,\"height\":0,\"width\":0,\"shotCount\":0,\"hitPoints\":0,\"sunkPoints\":0,\"roundTime\":0,\"visualizationTime\":0,\"penaltyMinusPoints\":0},\"tournament\":false}],\"messageId\":251}";
        assert parser.serialize(parser.deserialize(test)).equals(test);
        Message message = RequestBuilder.serverJoinRequest("hello", ClientType.PLAYER);
        assert parser.deserialize(parser.serialize(message)).equals(message);
    }

    @Test
    void serverJoinRequestParser() throws ParserException {
        Parser parser = new Parser();
        ServerJoinRequest message = RequestBuilder.serverJoinRequest("test",ClientType.PLAYER);
        assert parser.deserialize(parser.serialize(message)).equals(message);
    }

    @Test
    void serverJoinResponseParser() throws ParserException {
        assertMessage(ResponseBuilder.serverJoinResponse(randomId()));
    }

    @Test
    void lobbyRequestParser() throws ParserException {
        assertMessage(RequestBuilder.lobbyRequest());
    }

    @Test
    void lobbyResponseParser() throws ParserException {
        assertMessage(ResponseBuilder.lobbyResponse(Lists.newArrayList(new Game(randomId(),"test1", GameState.IN_PROGRESS, new Configuration.Builder().height(5).build(), true),new Game(randomId(),"test2", GameState.FINISHED, new Configuration.Builder().penaltyKind(PenaltyType.KICK).build(), true))));
    }

    @Test
    void gameJoinPlayerRequestParser() throws ParserException {
        assertMessage(RequestBuilder.gameJoinPlayerRequest(randomId()));
    }

    @Test
    void gameJoinPlayerResponseParser() throws ParserException {
        assertMessage(ResponseBuilder.gameJoinPlayerResponse(randomId()));
    }

    @Test
    void gameJoinSpectatorRequestParser() throws ParserException {
        assertMessage(RequestBuilder.gameJoinSpectatorRequest(randomId()));
    }

    @Test
    void gameJoinSpectatorResponseParser() throws ParserException {
        assertMessage(ResponseBuilder.gameJoinSpectatorResponse(randomId()));
    }

    @Test
    void placeShipsRequestParser() throws ParserException {
        assertMessage(RequestBuilder.placeShipsRequest(new HashMap<Integer, PlacementInfo>(){{
            for (int i = 0;i< 10;i++)
                put(randomId(),new PlacementInfo(new Point2D(randomId(),randomId()),Rotation.values()[RNG.nextInt(Rotation.values().length)]));
        }}));
    }

    @Test
    void placeShipsResponseParser() throws ParserException {
        assertMessage(ResponseBuilder.placeShipsResponse());
    }

    @Test
    void shotsRequestParser() throws ParserException {
        assertMessage(RequestBuilder.shotsRequest(Lists.newArrayList(new Shot(randomId(),new Point2D(randomId(),randomId())),new Shot(randomId(),new Point2D(randomId(),randomId())),new Shot(randomId(),new Point2D(randomId(),randomId())),new Shot(randomId(),new Point2D(randomId(),randomId())))));
    }

    @Test
    void shotsResponseParser() throws ParserException {
        assertMessage(ResponseBuilder.shotsResponse());
    }

    @Test
    void pointsRequestParser() throws ParserException {
        assertMessage(RequestBuilder.pointsRequest());
    }

    @Test
    void pointsResponseParser() throws ParserException {
        assertMessage(ResponseBuilder.pointsResponse(new HashMap<Integer, Integer>(){{
            for (int i = 0;i< 10;i++)
                put(randomId(),randomId());
        }}));
    }

    @Test
    void remainingTimeRequestParser() throws ParserException {
        assertMessage(RequestBuilder.remainingTimeRequest());
    }

    @Test
    void remainingTimeResponseParser() throws ParserException {
        assertMessage(ResponseBuilder.remainingTimeResponse(randomId()));
    }

    @Test
    void playerGameStateRequestParser() throws ParserException {
        assertMessage(RequestBuilder.playerGameStateRequest());
    }

    @Test
    void playerGameStateResponseParser() throws ParserException {
        assertMessage(ResponseBuilder.playerGameStateResponse(GameState.FINISHED,Lists.newArrayList(new Shot(randomId(),new Point2D(randomId(),randomId()))),Lists.newArrayList(new Shot(randomId(),new Point2D(randomId(),randomId()))),new HashMap<Integer, PlacementInfo>(){{
            for (int i = 0;i< 10;i++)
                put(randomId(),new PlacementInfo(new Point2D(randomId(),randomId()),Rotation.values()[RNG.nextInt(Rotation.values().length)]));
        }},Lists.newArrayList(new Client(randomId(),"client"+randomId()))));
    }

    @Test
    void spectatorGameStateRequestParser() throws ParserException {
        assertMessage(RequestBuilder.spectatorGameStateRequest());
    }

    @Test
    void spectatorGameStateResponseParser() throws ParserException {
        assertMessage(ResponseBuilder.spectatorGameStateResponse(Lists.newArrayList(new Client(randomId(),"client"+randomId())),Lists.newArrayList(new Shot(randomId(),new Point2D(randomId(),randomId()))),new HashMap<Integer, Map<Integer, PlacementInfo>>(){{
            for (int i = 0;i< 10;i++)
                put(randomId(),new HashMap<Integer, PlacementInfo>(){{
                    for (int i = 0;i< 10;i++)
                        put(randomId(),new PlacementInfo(new Point2D(randomId(),randomId()),Rotation.values()[RNG.nextInt(Rotation.values().length)]));
                }});
        }},GameState.PAUSED));
    }

    @Test
    void pauseNotificationParser() throws ParserException {
        assertMessage(NotificationBuilder.pauseNotification());
    }

    @Test
    void continueNotificationParser() throws ParserException {
        assertMessage(NotificationBuilder.continueNotification());
    }

    @Test
    void gameStartNotificationParser() throws ParserException {
        assertMessage(NotificationBuilder.gameStartNotification());
    }

    @Test
    void finishNotificationParser() throws ParserException {
        assertMessage(NotificationBuilder.finishNotification(new HashMap<Integer, Integer>(){{
            for (int i = 0;i< 10;i++)
                put(randomId(),randomId());
        }},Lists.newArrayList(randomId(),randomId())));
    }

    @Test
    void roundStartNotificationParser() throws ParserException {
        assertMessage(NotificationBuilder.roundStartNotification());
    }

    @Test
    void gameInitNotificationParser() throws ParserException {
        assertMessage(NotificationBuilder.gameInitNotification(Lists.newArrayList(new Client(randomId(),"test")),new Configuration.Builder().hitPoints(2).build()));
    }

    @Test
    void leaveNotificationParser() throws ParserException {
        assertMessage(NotificationBuilder.leaveNotification(randomId()));
    }

    @Test
    void spectatorUpdateNotificationParser() throws ParserException {
        assertMessage(NotificationBuilder.spectatorUpdateNotification(Lists.newArrayList(new Shot(randomId(),new Point2D(randomId(),randomId()))),new HashMap<Integer, Integer>(){{
            for (int i = 0;i< 10;i++)
                put(randomId(),randomId());
        }},Lists.newArrayList(new Shot(randomId(),new Point2D(randomId(),randomId()))),Lists.newArrayList(new Shot(randomId(),new Point2D(randomId(),randomId())))));
    }

    @Test
    void playerUpdateNotificationParser() throws ParserException {
        assertMessage(NotificationBuilder.playerUpdateNotification(Lists.newArrayList(new Shot(randomId(),new Point2D(randomId(),randomId()))),new HashMap<Integer, Integer>(){{
            for (int i = 0;i< 10;i++)
                put(randomId(),randomId());
        }},Lists.newArrayList(new Shot(randomId(),new Point2D(randomId(),randomId())))));
    }

    @Test
    void errorNotificationParser() throws ParserException {
        assertMessage(NotificationBuilder.errorNotification(ErrorType.BAD_JSON,randomId(),"failed om purpose"));
    }

    private void assertMessage(Message message) throws ParserException {
        Parser parser = new Parser();
        assert parser.deserialize(parser.serialize(message)).equals(message);
    }

    private int randomId(){
        return RNG.nextInt(Integer.MAX_VALUE);
    }
}
