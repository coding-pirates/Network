package de.upb.codingpirates.battleships.server.handler;

import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.ConnectionHandler;
import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.handler.MessageHandler;
import de.upb.codingpirates.battleships.network.test.NetworkTests;
import de.upb.codingpirates.battleships.network.test.TestMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

/**
 * @author Paul Becker
 */
public class TestMessageHandler implements MessageHandler<TestMessage> {
    private static final Logger LOGGER = LogManager.getLogger();

    private final NetworkTests.ClientManager manager;

    @Inject
    public TestMessageHandler(ConnectionHandler manager) {
        this.manager = (NetworkTests.ClientManager) manager;
    }

    @Override
    public boolean canHandle(@Nonnull Message message) {
        return true;
    }

    @Override
    public void handle(@Nonnull TestMessage message, @Nonnull Id connectionId) {
        this.manager.create(new Id(0), "hello");
        LOGGER.info("message received on server successful");
    }
}
