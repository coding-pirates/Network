package de.upb.codingpirates.battleships.network.test;

import de.upb.codingpirates.battleships.logic.ClientType;
import de.upb.codingpirates.battleships.network.exceptions.parser.ParserException;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.Parser;
import de.upb.codingpirates.battleships.network.message.request.RequestBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class ParserTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    void parser() throws ParserException {
        Parser parser = new Parser();
        String test = "{\"games\":[{\"name\":\"testomat\",\"id\":0,\"currentPlayerCount\":5,\"state\":\"LOBBY\",\"config\":{\"maxPlayerCount\":0,\"height\":0,\"width\":0,\"shotCount\":0,\"hitPoints\":0,\"sunkPoints\":0,\"roundTime\":0,\"visualizationTime\":0,\"penaltyMinusPoints\":0},\"tournament\":false}],\"messageId\":251}";
        assert parser.serialize(parser.deserialize(test)).equals(test);
        Message message = RequestBuilder.serverJoinRequest("hello", ClientType.PLAYER);
        assert parser.deserialize(parser.serialize(message)).equals(message);

    }
}
