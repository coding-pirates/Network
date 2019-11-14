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

    private Connection connection;
    private Observable<Connection> observer;

    public void connect(String host, int port) throws IOException {
        LOGGER.debug("trying to connect to {}:{}", host, port);
        this.connection = new Connection(new IntId(0), new Socket(host, port));
        LOGGER.debug("Connected to {}", connection.getInetAdress());
    }

    public void setObserver(Observable<Connection> observer) {
        this.observer = observer;
    }
}
