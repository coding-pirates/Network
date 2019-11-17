package de.upb.codingpirates.battleships.network.network;

import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.annotations.bindings.FixedThreadPool;
import de.upb.codingpirates.battleships.network.connectionmanager.ServerConnectionManager;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * The ServerNetwork handles all new Connections from Clients and saves them in the {@link ServerConnectionManager}.
 * <p></p>
 * Its also stores the observer for incoming messages.
 *
 * @author Paul Becker
 */
public class ServerNetwork implements Network {
    private static final Logger LOGGER = LogManager.getLogger();

    private ServerSocket socket;
    private Observable<Connection> connections;
    private ExecutorService threadPool;
    private ServerConnectionManager connectionManager;

    /**
     * Creates a {@link ServerNetwork}, gets a fixed Threadpool and InetSocketAddress.
     * <p></p>
     * Initiates the ServerSocket to work based of the {@link InetSocketAddress} and Creates a Observer Pattern, that waits for new connections and binds them in the {@link ServerConnectionManager}
     *
     * @throws IOException
     */
    @Inject
    public ServerNetwork(@FixedThreadPool(size = 1) ExecutorService executorService, InetSocketAddress address, ServerConnectionManager connectionManager) throws IOException {
        this.threadPool = executorService;
        this.socket = new ServerSocket();
        this.connectionManager = connectionManager;

        this.init(address);
    }

    public void init(InetSocketAddress address) {
        LOGGER.debug("Initiate server network");

        try {
            socket.bind(address);
        } catch (IOException e) {
            LOGGER.error("Exception while trying to bind server socket", e);
            return;
        }

        this.connections = Observable.create(this::acceptLoop).subscribeOn(Schedulers.from(this.threadPool));
    }

    /**
     * Accept loop that waits for connections with {@link ServerSocket#accept()} and creates new {@link Connection} on success.
     * <p></p>
     * Also it "informes the {@link Observable} about new created Connections to listen to.
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

    public Observable<Connection> getConnections() {
        return connections;
    }

    public boolean isClosed() {
        return this.socket.isClosed();
    }

    public void close() throws IOException {
        this.socket.close();
    }
}
