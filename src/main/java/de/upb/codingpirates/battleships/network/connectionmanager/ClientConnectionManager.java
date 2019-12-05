package de.upb.codingpirates.battleships.network.connectionmanager;

import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.dispatcher.ClientMessageDispatcher;
import de.upb.codingpirates.battleships.network.id.IntId;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Paul Becker
 */
public class ClientConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ClientConnectionManager.class.getName());
    @Nonnull
    private final ClientMessageDispatcher messageDispatcher;
    @Nullable
    private Connection connection;

    @Inject
    public ClientConnectionManager(@Nonnull ClientMessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    public void create(@Nonnull String host, int port) throws IOException {
        this.connection = this.messageDispatcher.connect(host, port);
    }

    @Nullable
    public Connection getConnection() {
        return connection;
    }

    public void send(Message message) throws IOException {
        if (this.connection == null)
            LOGGER.log(Level.SEVERE, "Client connection is not established");
        else
            this.connection.send(message);
    }

    public void disconnect() throws IOException {
        if (this.connection == null)
            LOGGER.log(Level.SEVERE, "Client connection is not established");
        else
            this.connection.close();
    }

    /**
     * sets connection id to new id
     *
     * @param id new Id
     */
    public void setConnectionId(int id) {
        assert this.connection != null;
        this.connection.setId(new IntId(id));
    }
}
