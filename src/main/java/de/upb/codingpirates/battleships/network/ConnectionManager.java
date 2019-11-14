package de.upb.codingpirates.battleships.network;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.id.IdManager;
import de.upb.codingpirates.battleships.network.message.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/**
 * @author Paul Becker
 */
public class ConnectionManager {

    private IdManager idManager;
    private final Map<Integer, Connection> connections = Maps.newHashMap();

    @Inject
    public ConnectionManager(IdManager idManager) {
        this.idManager = idManager;
    }

    public Connection create(Socket socket) throws IOException {
        Connection connection = new Connection(this.idManager.generate(), socket);

        synchronized (connections) {
            this.connections.put((Integer) connection.getId().getRaw(), connection);
        }

        return connection;
    }

    @SuppressWarnings("RedundantCast")
    public Connection getConnection(Id id) {
        synchronized (this.connections) {
            return this.connections.get((Integer) id.getRaw());
        }
    }

    @SuppressWarnings("RedundantCast")
    public void send(Id id, Message message) throws IOException {
        synchronized (this.connections) {
            this.connections.get((Integer) id.getRaw()).send(message);
        }
    }

    public void sendAll(Message message) throws IOException {
        synchronized (this.connections) {
            for (Connection connection : this.connections.values()) {
                connection.send(message);
            }
        }
    }
}
