package de.upb.codingpirates.battleships.server.handler;

import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.ConnectionHandler;
import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.id.IntId;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.MessageHandler;
import de.upb.codingpirates.battleships.network.test.NetworkTests;
import de.upb.codingpirates.battleships.network.test.TestMessage;

import java.util.logging.Logger;

/**
 * @author Paul Becker
 */
public class TestMessageHandler implements MessageHandler<TestMessage> {
    private static final Logger LOGGER = Logger.getLogger(TestMessageHandler.class.getName());

    private final NetworkTests.ClientManager manager;

    @Inject
    public TestMessageHandler(ConnectionHandler manager) {
        this.manager = (NetworkTests.ClientManager) manager;
    }

    @Override
    public boolean canHandle(Message message) {
        return true;
    }

    @Override
    public void handle(TestMessage message, Id connectionId) {
        this.manager.create(new IntId(0), "hello");
        LOGGER.info("message received on server successful");
    }
}
