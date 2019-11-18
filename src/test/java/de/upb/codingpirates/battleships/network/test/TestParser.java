package de.upb.codingpirates.battleships.network.test;

import de.upb.codingpirates.battleships.network.Parser;
import de.upb.codingpirates.battleships.network.message.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

/**
 * @author Paul Becker
 */
public class TestParser implements Parser { //TODO remove
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Message deserialize(@Nonnull String message) {
        if (message.equals("test"))
            return new TestMessage();
        LOGGER.error("Cant parse Message");
        return null;
    }

    @Override
    public String serialize(@Nonnull Message message) {
        return "test";
    }
}
