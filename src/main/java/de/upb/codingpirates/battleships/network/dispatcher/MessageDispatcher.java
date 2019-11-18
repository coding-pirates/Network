package de.upb.codingpirates.battleships.network.dispatcher;

import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.message.Message;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;

/**
 * Necessary for Guice to get a class depending on distribution.
 *
 * @author Paul Becker
 */
public interface MessageDispatcher {

    default void loop(Connection connection, Consumer<? super Pair<Connection, Message>> onNext, Consumer<? super Throwable> onError) {
        Observable.create((ObservableEmitter<Pair<Connection, Message>> emitter) -> {
            while (!connection.isClosed()) {
                try {
                    Message message = connection.read();
                    emitter.onNext(new Pair<>(connection, message));
                } catch (IOException e) {
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe(onNext, onError);
    }

}
