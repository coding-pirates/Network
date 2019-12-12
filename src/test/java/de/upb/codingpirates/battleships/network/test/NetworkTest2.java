package de.upb.codingpirates.battleships.network.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.Test;

import de.upb.codingpirates.battleships.network.exceptions.parser.ParserException;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.Parser;

public class NetworkTest2 {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    void testParser() throws ParserException {
        final Parser  parser  = new Parser();
        final Message message = parser.deserialize("{'games':[{'name':'test','id':0,'currentPlayerCount':5,\"state\":\"LOBBY\",\"config\":{\"MAXPLAYERCOUNT\":0,\"HEIGHT\":0,\"WIDTH\":0,\"SHOTCOUNT\":0,\"HITPOINTS\":0,\"SUNKPOINTS\":0,\"ROUNDTIME\":0,\"VISUALIZATIONTIME\":0,\"SHIPS\":{\"1\":{\"position\":[{\"x\":1,\"y\":2}]}},\"PENALTYMINUSPOINTS\":1,\"PENALTYTYPE\":\"POINTLOSS\"},\"tournament\":false}],\"messageId\":251}");

        LOGGER.info(parser.serialize(message));
    }
}
