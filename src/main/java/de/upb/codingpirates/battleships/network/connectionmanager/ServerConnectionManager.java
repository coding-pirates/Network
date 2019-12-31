package de.upb.codingpirates.battleships.network.connectionmanager;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.id.IdManager;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.notification.ErrorNotification;
import de.upb.codingpirates.battleships.network.util.NetworkMarker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.Map;

/**
 * Stores all {@link Connection}'s for client {@link Id}s
 *
 * @author Paul Becker
 */
public class ServerConnectionManager {

    @Nonnull
    private static final Logger LOGGER = LogManager.getLogger();

    @Nonnull
    private final Map<Integer, Connection> connections = Collections.synchronizedMap(Maps.newHashMap());
    @Inject
    private IdManager idManager;

    public @Nonnull
    Connection create(Socket socket) throws IOException {
        Connection connection = new Connection(this.idManager.generate(), socket);

        this.connections.put( connection.getId().getInt(), connection);

        return connection;
    }

    public Connection getConnection(@Nonnull Id id) {
        return this.connections.get(id.getInt());
    }

    public void send(@Nonnull Id id, @Nonnull Message message) throws IOException {
        LOGGER.debug(NetworkMarker.MESSAGE, "sending Message {} to client {}", message.getClass(), id);
        if (message instanceof ErrorNotification) {
            LOGGER.debug("client {} error: {}, {}, {}", id, ((ErrorNotification) message).getErrorType(), ((ErrorNotification) message).getReferenceMessageId(), ((ErrorNotification) message).getReason());
        }
        this.connections.get(id.getInt()).send(message);
    }
}
