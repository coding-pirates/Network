package de.upb.codingpirates.battleships.network.util;

import java.io.IOException;
import java.net.SocketException;

import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.exceptions.parser.ParserException;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.report.ConnectionClosedReport;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DefaultReaderMethod implements ClientReaderMethod {
    @Override
    public void get(Connection connection, Consumer<Pair<Connection, Message>> dispatch, Consumer<Throwable> error) {
        Observable.create((ObservableEmitter<Pair<Connection, Message>> emitter) -> {

            while (!connection.isClosed()) {
                try {
                    Message message = connection.read();
                    emitter.onNext(new Pair<>(connection, message));
                } catch (SocketException e) {
                    connection.close();
                    emitter.onNext(new Pair<>(connection, new ConnectionClosedReport()));
                } catch (IOException | ParserException e) {
                    emitter.onError(e);
                }

            }
        }).subscribeOn(Schedulers.io()).subscribe(dispatch, error);
    }
}
