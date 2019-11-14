package de.upb.codingpirates.battleships.network;

import de.upb.codingpirates.battleships.network.message.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Paul Becker
 */
public class TestParser implements Parser {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Message deserialize(String message) {
        if (message.equals("test"))
            return new TestMessage();
        LOGGER.error("Cant parse Message");
        return null;
    }

    @Override
    public String serialize(Message message) {
        return "test";
    }
}
