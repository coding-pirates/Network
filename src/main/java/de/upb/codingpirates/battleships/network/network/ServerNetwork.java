package de.upb.codingpirates.battleships.network.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.google.common.base.Preconditions;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.annotations.bindings.FixedThreadPool;
import de.upb.codingpirates.battleships.network.connectionmanager.ServerConnectionManager;
import de.upb.codingpirates.battleships.network.util.NetworkMarker;

/**
 * The ServerNetwork handles all new Connections from Clients and saves them in the {@link ServerConnectionManager}.
 * <p></p>
 * Its also stores the observer for incoming messages.
 *
 * @author Paul Becker
 */
public class ServerNetwork implements Network {
    private static final Logger LOGGER = LogManager.getLogger();

    @Nonnull
    private ServerSocket socket;
    @Nonnull
    private Observable<Connection> connections;
    @Nonnull
    private ServerConnectionManager connectionManager;

    /**
     * Creates a {@link ServerNetwork}, gets a fixed ThreadPool and InetSocketAddress.
     * <p></p>
     * Initiates the ServerSocket to work based of the {@link InetSocketAddress} and Creates a Observer Pattern, that waits for new connections and binds them in the {@link ServerConnectionManager}
     */
    @Inject
    public ServerNetwork(@Nonnull @FixedThreadPool(size = 1) ExecutorService executorService, @Nonnull InetSocketAddress address, @Nonnull ServerConnectionManager connectionManager) throws IOException {
        Preconditions.checkNotNull(executorService);
        Preconditions.checkNotNull(address);
        Preconditions.checkNotNull(connectionManager);

        LOGGER.info(NetworkMarker.NETWORK,"Initiate server network");

        this.socket = new ServerSocket();
        this.connectionManager = connectionManager;

        try {
            socket.bind(address);
        } catch (IOException e) {
            LOGGER.error(NetworkMarker.NETWORK,"Exception while trying to bind server socket", e);
            throw new IllegalStateException("Could not bind SocketAddress");
        }
        this.connections = Observable.create(this::acceptLoop).subscribeOn(Schedulers.from(executorService));
    }

    /**
     * Accept loop that waits for connections with {@link ServerSocket#accept()} and creates new {@link Connection} on success.
     * <p></p>
     * Also it "informs the {@link Observable} about new created Connections to listen to.
     */
    private void acceptLoop(ObservableEmitter<Connection> emitter) {
        while (!isClosed()) {
            try {
                Socket client = this.socket.accept();
                if (client != null && client.isConnected()) {
                    Connection connection = this.connectionManager.create(client);
                    emitter.onNext(connection);
                }
            } catch (IOException e) {
                emitter.onError(e);
            }
        }
    }

    @Nonnull
    public Observable<Connection> getConnections() {
        return connections;
    }

    public boolean isClosed() {
        return this.socket.isClosed();
    }
}
