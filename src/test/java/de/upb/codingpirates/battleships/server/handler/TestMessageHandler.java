package de.upb.codingpirates.battleships.server.handler;

import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.Handler;
import de.upb.codingpirates.battleships.network.TestMessage;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.MessageHandler;
import de.upb.codingpirates.battleships.network.test.NetworkTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Paul Becker
 */
public class TestMessageHandler implements MessageHandler<TestMessage> {
    private static final Logger LOGGER = LogManager.getLogger();

    private NetworkTests.ClientManager manager;

    @Inject
    public TestMessageHandler(Handler manager) {
        this.manager = (NetworkTests.ClientManager) manager;
    }

    @Override
    public boolean canHandle(Message message) {
        return true;
    }

    @Override
    public void handle(Message message) {
        this.manager.create(message.getId(), "hello");
        LOGGER.info("message received on server successful");
    }
}
