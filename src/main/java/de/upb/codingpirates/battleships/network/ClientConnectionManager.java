package de.upb.codingpirates.battleships.network;

import de.upb.codingpirates.battleships.network.id.IntId;
import de.upb.codingpirates.battleships.network.message.Message;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Paul Becker
 */
public class ClientConnectionManager {

    private Connection connection;


    public Connection create(Socket socket) throws IOException {
        return this.connection = new Connection(new IntId(0), socket);
    }

    public Connection getConnection() {
        return connection;
    }

    public void send(Message message) throws IOException {
        this.connection.send(message);
    }
}
