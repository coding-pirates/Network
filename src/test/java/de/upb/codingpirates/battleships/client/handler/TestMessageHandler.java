package de.upb.codingpirates.battleships.client.handler;

import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.ConnectionHandler;
import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.MessageHandler;
import de.upb.codingpirates.battleships.network.test.NetworkTests;
import de.upb.codingpirates.battleships.network.test.TestMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Paul Becker
 */
public class TestMessageHandler implements MessageHandler<TestMessage> {
    private static final Logger LOGGER = LogManager.getLogger();

    private NetworkTests.ClientConnector connector;

    @Inject
    public TestMessageHandler(ConnectionHandler manager) {
        this.connector = (NetworkTests.ClientConnector) manager;
    }

    @Override
    public boolean canHandle(Message message) {
        return true;
    }

    @Override
    public void handle(TestMessage message, Id connectionId) {
        LOGGER.info("message received on client successful");
    }


}
