package de.upb.codingpirates.battleships.network.util;

import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.message.Message;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DefaultReaderMethod extends ClientReaderMethod {

    @Override
    public void get(Connection connection, Consumer<Pair<Connection, Message>> dispatch, Consumer<Throwable> error) {
        this.create(connection).subscribeOn(Schedulers.io()).subscribe(dispatch, error);
    }
}
