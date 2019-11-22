package de.upb.codingpirates.battleships.network.connectionmanager;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.id.IdManager;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/**
 * Stores all {@link Connection}'s for client {@link Id}s
 *
 * @author Paul Becker
 */
public class ServerConnectionManager {

    private @Nonnull
    final Map<Integer, Connection> connections = Maps.newHashMap();
    @Inject
    private @Nonnull
    IdManager idManager;

    public @Nonnull
    Connection create(Socket socket) throws IOException {
        Connection connection = new Connection(this.idManager.generate(), socket);

        synchronized (connections) {
            this.connections.put((Integer) connection.getId().getRaw(), connection);
        }

        return connection;
    }

    @SuppressWarnings("RedundantCast")
    public Connection getConnection(@Nonnull Id id) {
        synchronized (this.connections) {
            return this.connections.get((Integer) id.getRaw());
        }
    }

    @SuppressWarnings("RedundantCast")
    public void send(@Nonnull Id id, @Nonnull Message message) throws IOException {
        synchronized (this.connections) {
            this.connections.get((Integer) id.getRaw()).send(message);
        }
    }
}
