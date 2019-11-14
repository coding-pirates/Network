package de.upb.codingpirates.battleships.client.handler;

import de.upb.codingpirates.battleships.network.TestMessage;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.MessageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Paul Becker
 */
public class TestMessageHandler implements MessageHandler<TestMessage> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public boolean canHandle(Message message) {
        return true;
    }

    @Override
    public void handle(Message message) {
        LOGGER.info("-------------------------------------");
        LOGGER.info("message received on client successful");
        LOGGER.info("-------------------------------------");
    }
}
