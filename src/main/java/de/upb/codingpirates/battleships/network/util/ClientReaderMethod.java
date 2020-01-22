package de.upb.codingpirates.battleships.network.util;

import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.exceptions.parser.ParserException;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.report.ReportBuilder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.net.SocketException;

public class ClientReaderMethod {

    public void get(Connection connection, Consumer<Pair<Connection, Message>> dispatch, Consumer<Throwable> error){
        this.create(connection).subscribeOn(Schedulers.io()).subscribe(dispatch, error);
    }

    protected Observable<Pair<Connection, Message>> create(Connection connection){
        return Observable.create((ObservableEmitter<Pair<Connection, Message>> emitter) -> {
            while (connection.isOpen()) {
                try {
                    Message message = connection.read();
                    emitter.onNext(new Pair<>(connection, message));
                } catch (SocketException e) {
                    connection.close();
                    emitter.onNext(new Pair<>(connection, ReportBuilder.connectionClosedReport()));
                } catch (IOException | ParserException e) {
                    emitter.onError(e);
                }

            }
        });
    }
}
