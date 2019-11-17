package de.upb.codingpirates.battleships.network.network;

import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.id.IntId;
import io.reactivex.Observable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Paul Becker
 */
public class ClientNetwork implements Network {
    private static final Logger LOGGER = LogManager.getLogger();

    private Observable<Connection> observer;

    public Connection connect(String host, int port) throws IOException {
        LOGGER.debug("trying to connect to {}:{}", host, port);
        return new Connection(new IntId(0), new Socket(host, port));
    }

    public void setObserver(Observable<Connection> observer) {
        this.observer = observer;
    }
}
