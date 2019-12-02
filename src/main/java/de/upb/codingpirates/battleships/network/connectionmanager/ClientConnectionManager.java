package de.upb.codingpirates.battleships.network.connectionmanager;

import com.google.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.dispatcher.ClientMessageDispatcher;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;

/**
 * @author Paul Becker
 */
public class ClientConnectionManager {
    private static final Logger LOGGER = LogManager.getLogger(ClientConnectionManager.class.getName());

    private @Nullable
    Connection connection;
    @Inject
    private @Nonnull
    ClientMessageDispatcher messageDispatcher;

    public void create(@Nonnull String host, int port) throws IOException {
        this.connection = this.messageDispatcher.connect(host, port);
    }

    @Nullable
    public Connection getConnection() {
        return connection;
    }

    public void send(Message message) throws IOException {
        if (this.connection == null)
            LOGGER.error("Client connection is not established");
        else
            this.connection.send(message);
    }

    public void disconnect() throws IOException {
        if (this.connection == null)
            LOGGER.error("Client connection is not established");
        else
            this.connection.close();
    }
}
