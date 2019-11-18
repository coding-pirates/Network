package de.upb.codingpirates.battleships.network.network;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.Parser;
import de.upb.codingpirates.battleships.network.id.IntId;
import io.reactivex.Observable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.Socket;

/**
 * The ClientNetwork only stores the observer for incoming messages.
 *
 * @author Paul Becker
 */
public class ClientNetwork implements Network {
    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("FieldCanBeLocal")
    private Observable<Connection> observer;
    @Inject
    private @Nonnull
    Parser parser;

    /**
     * Creates a new Socket with the given parameters.
     *
     * @return created Connection
     */
    public Connection connect(@Nonnull String host, int port) throws IOException {
        Preconditions.checkNotNull(host);

        LOGGER.debug("trying to connect to {}:{}", host, port);
        return new Connection(new IntId(0), new Socket(host, port), parser);
    }

    public void setObserver(Observable<Connection> observer) {
        this.observer = observer;
    }
}
