package de.upb.codingpirates.battleships.network.util;

import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.message.Message;
import io.reactivex.functions.Consumer;

public interface ClientReaderMethod {

    void get(Connection connection, Consumer<Pair<Connection, Message>> dispatch, Consumer<Throwable> error);
}
