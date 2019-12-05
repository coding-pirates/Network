package de.upb.codingpirates.battleships.network.network;

import com.google.common.base.Preconditions;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.id.IntId;
import io.reactivex.Observable;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * The ClientNetwork only stores the observer for incoming messages.
 *
 * @author Paul Becker
 */
public class ClientNetwork implements Network {
    private static final Logger LOGGER = Logger.getLogger(ClientNetwork.class.getName());

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private Observable<Connection> observer;

    /**
     * Creates a new Socket with the given parameters.
     *
     * @return created Connection
     */
    public Connection connect(@Nonnull String host, int port) throws IOException {
        Preconditions.checkNotNull(host);

        LOGGER.info("trying to connect to " + host + ":" + port);
        return new Connection(new IntId(0), new Socket(host, port));
    }

    public void setObserver(Observable<Connection> observer) {
        this.observer = observer;
    }
}
