package de.upb.codingpirates.battleships.network.message;

import de.upb.codingpirates.battleships.network.Connection;

/**
 * @author Paul Becker
 */
public class Request {

    private Connection connection;
    private Message message;

    public Request(Connection connection, Message message) {
        this.connection = connection;
        this.message = message;
    }

    public Connection getConnection() {
        return connection;
    }

    public Message getMessage() {
        return message;
    }
}
