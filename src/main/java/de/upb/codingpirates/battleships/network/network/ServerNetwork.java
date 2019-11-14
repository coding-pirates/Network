package de.upb.codingpirates.battleships.network.network;

import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.ConnectionManager;
import de.upb.codingpirates.battleships.network.annotations.bindings.FixedThreadPool;
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
 * @author Paul Becker
 */
public class ServerNetwork implements Network {
    private static final Logger LOGGER = LogManager.getLogger();

    private ServerSocket socket;
    private Observable<Connection> connections;
    private ExecutorService threadPool;
    private ConnectionManager connectionManager;

    @Inject
    public ServerNetwork(@FixedThreadPool(size = 1) ExecutorService executorService, InetSocketAddress address, ConnectionManager connectionManager) throws IOException {
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
            LOGGER.error("Exception while trying to bind socket", e);
            return;
        }

        this.connections = Observable.create(this::acceptLoop).subscribeOn(Schedulers.from(this.threadPool));
    }

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
