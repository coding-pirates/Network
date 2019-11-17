package de.upb.codingpirates.battleships.network;

import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.dispatcher.ClientMessageDispatcher;
import de.upb.codingpirates.battleships.network.message.Message;

import java.io.IOException;

/**
 * @author Paul Becker
 */
public class ClientConnectionManager {

    private Connection connection;
    private ClientMessageDispatcher messageDispatcher;

    @Inject
    public ClientConnectionManager(ClientMessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    public void create(String host, int port) throws IOException {
        this.connection = this.messageDispatcher.connect(host, port);
    }

    public Connection getConnection() {
        return connection;
    }

    public void send(Message message) throws IOException {
        this.connection.send(message);
    }
}
