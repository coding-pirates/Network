package de.upb.codingpirates.battleships.network.util;

import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.message.Message;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DefaultReaderMethod extends ClientReaderMethod {

    /**
     * creates an observer for a connection and the specified consumer
     * and applies a I/O Thread scheduler on the observable
     *
     * @param connection the connection to be observed
     * @param dispatch the consumer to consume a message
     * @param error the consumer to handle if an error occurs
     */
    @Override
    public void get(Connection connection, Consumer<Pair<Connection, Message>> dispatch, Consumer<Throwable> error) {
        this.create(connection).subscribeOn(Schedulers.io()).subscribe(dispatch, error);
    }
}
