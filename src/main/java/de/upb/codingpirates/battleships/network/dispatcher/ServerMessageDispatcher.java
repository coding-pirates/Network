package de.upb.codingpirates.battleships.network.dispatcher;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.ConnectionHandler;
import de.upb.codingpirates.battleships.network.exceptions.BattleshipException;
import de.upb.codingpirates.battleships.network.exceptions.game.GameException;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.MessageHandler;
import de.upb.codingpirates.battleships.network.message.report.ConnectionClosedReport;
import de.upb.codingpirates.battleships.network.network.ServerNetwork;
import de.upb.codingpirates.battleships.network.scope.ConnectionScope;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@link ServerMessageDispatcher} registers a read loop for the {@link Observable} of the {@link ServerNetwork}. The read loop {@link ServerMessageDispatcher#dispatch(Pair)} a request if it receives a message.
 *
 * @author Paul Becker
 */
public class ServerMessageDispatcher implements MessageDispatcher {
    private static final Logger LOGGER = Logger.getLogger(ServerMessageDispatcher.class.getName());

    private final ConnectionScope scope;
    private final Injector injector;
    private final ConnectionHandler connectionHandler;

    @Inject
    public ServerMessageDispatcher(ServerNetwork network, Injector injector, ConnectionScope scope, ConnectionHandler connectionHandler) {
        LOGGER.log(Level.ALL,"Initialize server message dispatcher");

        this.scope = scope;
        this.injector = injector;
        this.connectionHandler = connectionHandler;

        if (network == null || network.isClosed()) {
            LOGGER.log(Level.WARNING,"Server network is not working");
            return;
        }

        network.getConnections().subscribe(this::readLoop);
    }


    private void readLoop(Connection connection) {
        LOGGER.info("Connection from "+connection.getInetAdress() );
        Observable.create((ObservableEmitter<Pair<Connection, Message>> emitter) -> {
            while (!connection.isClosed()) {
                try {
                    Message message = connection.read();
                    emitter.onNext(new Pair<>(connection, message));
                }catch (SocketException e){
                    connection.close();
                    emitter.onNext(new Pair<>(connection, new ConnectionClosedReport()));
                } catch (IOException e) {
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe(this::dispatch, this::error);
    }

    /**
     * Called if the {@link Observable} gets a message.
     * <p></p>
     * <p>
     * It tries to get a {@link MessageHandler} based on the name of the message and tries to let the MessageHandler handle the message. Otherwise logs th failure.
     *
     * @param request
     */
    @SuppressWarnings("unchecked")
    private void dispatch(Pair<Connection, Message> request) {
        try {
            String[] namespace = request.getValue().getClass().getName().split("\\.");
            String name = namespace[namespace.length - 1];
            Class<?> type;
            type = Class.forName("de.upb.codingpirates.battleships.server.handler." + name + "Handler");
            this.scope.seed(Connection.class, request.getKey());
            this.scope.enter(request.getKey().getId());

            MessageHandler handler = (MessageHandler) injector.getInstance(type);
            if (handler == null) {
                LOGGER.info("Can't find MessageHandler "+type+" for Message "+request.getValue().getClass());
            } else {
                if (handler.canHandle(request.getValue())) {
                    handler.handle(request.getValue(), request.getKey().getId());
                }
            }
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.ALL,"Can't find MessageHandler for Message", e);
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
            LOGGER.log(Level.ALL, "Error while reading Messages on Server", throwable);
        }
    }
}
