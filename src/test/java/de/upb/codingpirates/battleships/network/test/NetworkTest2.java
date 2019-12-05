package de.upb.codingpirates.battleships.network.test;

import de.upb.codingpirates.battleships.network.exceptions.parser.ParserException;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class NetworkTest2 {
    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    void parser() throws ParserException {
        Parser parser = new Parser();
//        Message message = parser.deserialize("{'positions':{'1':{'position':{'x':1,'y':2},'rotation':'2'}},'messageId':301}");
//        LOGGER.debug(((PlaceShipsRequest)message).getPositions().get(1).getRotation());
//        LOGGER.debug(parser.serialize(message));
//        LOGGER.debug(parser.serialize(new PlaceShipsRequest(new HashMap<Integer, PlacementInfo>(){{put(1,new PlacementInfo(new Point2D(1,2), Rotation.CLOCKWISE_90));}})));
//        LOGGER.debug(parser.serialize(new LobbyResponse(Lists.newArrayList(new Game("teetotal",0,5, GameState.LOBBY,Configuration.DEFAULT)))));
        Message message = parser.deserialize("{'games':[{'name':'teetotal','id':0,'currentPlayerCount':5,\"state\":\"LOBBY\",\"config\":{\"MAXPLAYERCOUNT\":0,\"HEIGHT\":0,\"WIDTH\":0,\"SHOTCOUNT\":0,\"HITPOINTS\":0,\"SUNKPOINTS\":0,\"ROUNDTIME\":0,\"VISUALIZATIONTIME\":0,\"SHIPS\":{\"1\":{\"position\":[{\"x\":1,\"y\":2}]}},\"PENALTYMINUSPOINTS\":1,\"PENALTYTYPE\":\"POINTLOSS\"},\"tournament\":false}],\"messageId\":251}");
        LOGGER.debug(parser.serialize(message));
    }
}
