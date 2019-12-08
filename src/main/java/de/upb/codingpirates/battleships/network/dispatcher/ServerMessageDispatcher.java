package de.upb.codingpirates.battleships.network.dispatcher;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.upb.codingpirates.battleships.logic.util.Dist;
import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.ConnectionHandler;
import de.upb.codingpirates.battleships.network.exceptions.BattleshipException;
import de.upb.codingpirates.battleships.network.exceptions.game.GameException;
import de.upb.codingpirates.battleships.network.exceptions.parser.ParserException;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.MessageHandler;
import de.upb.codingpirates.battleships.network.message.report.ConnectionClosedReport;
import de.upb.codingpirates.battleships.network.network.ServerNetwork;
import de.upb.codingpirates.battleships.network.scope.ConnectionScope;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.SocketException;

/**
 * The {@link ServerMessageDispatcher} registers a read loop for the {@link Observable} of the {@link ServerNetwork}. The read loop {@link ServerMessageDispatcher#dispatch(Pair)} a request if it receives a message.
 *
 * @author Paul Becker
 */
public class ServerMessageDispatcher implements MessageDispatcher {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ConnectionScope scope;
    private final Injector injector;
    private final ConnectionHandler connectionHandler;

    @Inject
    public ServerMessageDispatcher(ServerNetwork network, Injector injector, ConnectionScope scope, ConnectionHandler connectionHandler) {
        LOGGER.info("Initialize server message dispatcher");

        this.scope = scope;
        this.injector = injector;
        this.connectionHandler = connectionHandler;

        if (network == null || network.isClosed()) {
            LOGGER.warn("Server network is not working");
            return;
        }

        //noinspection ResultOfMethodCallIgnored
        network.getConnections().subscribe(this::readLoop);
    }


    private void readLoop(Connection connection) {
        LOGGER.info("Connection from " + connection.getInetAdress());
        get(connection, this::dispatch, this::error);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void get(Connection connection, Consumer<Pair<Connection, Message>> dispatch, Consumer<Throwable> error) {
        Observable.create((ObservableEmitter<Pair<Connection, Message>> emitter) -> {
            while (!connection.isClosed()) {
                try {
                    Message message = connection.read();
                    emitter.onNext(new Pair<>(connection, message));
                    break;
                } catch (SocketException e) {
                    connection.close();
                    emitter.onNext(new Pair<>(connection, new ConnectionClosedReport()));
                } catch (IOException | ParserException e) {
                    emitter.onError(e);
                }

            }
            emitter.onComplete();
        }).subscribeOn(Schedulers.io()).doOnDispose(() -> get(connection, dispatch, error)).doOnComplete(() -> get(connection, dispatch, error)).subscribe(dispatch, error);
    }

    /**
     * Called if the {@link Observable} gets a message.
     * <p></p>
     * <p>
     * It tries to get a {@link MessageHandler} based on the name of the message and tries to let the MessageHandler handle the message. Otherwise logs th failure.
     *
     * @param request a {@link Pair} of a Message and its Connection
     */
    private void dispatch(Pair<Connection, Message> request) {
        try {
            handleMessage(request, Dist.SERVER, this.scope, this.injector, LOGGER);
        } catch (ClassNotFoundException e) {
            LOGGER.info("Can't find MessageHandler for Message", e);
        } catch (GameException e) {
            this.connectionHandler.handleBattleshipException(e);
        } finally {
            this.scope.exit();
        }
    }

    private void error(Throwable throwable) {
        if (throwable instanceof BattleshipException)
            this.connectionHandler.handleBattleshipException((BattleshipException) throwable);
        else {
            LOGGER.info("Error while reading Messages on Server", throwable);
        }
    }
}
