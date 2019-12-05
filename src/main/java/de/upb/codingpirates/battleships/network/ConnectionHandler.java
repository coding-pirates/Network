package de.upb.codingpirates.battleships.network;

import de.upb.codingpirates.battleships.logic.util.Pair;
import de.upb.codingpirates.battleships.network.exceptions.BattleshipException;
import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Interface for the ConnectionHandler on Server and Client for sending messages. Necessary for Guice to get a class depending on distribution.
 *
 * @author Paul Becker
 */
public interface ConnectionHandler {

    /**
     * called from {@link de.upb.codingpirates.battleships.network.dispatcher.ServerMessageDispatcher#dispatch(Pair)} and {@link de.upb.codingpirates.battleships.network.dispatcher.ClientMessageDispatcher#dispatch(Pair)} if a {@link BattleshipException} is thrown inside a {@link de.upb.codingpirates.battleships.network.message.MessageHandler#handle(Message, Id)}
     *
     * @param e a BattleshipException which can be sent to the other distribution to show there failures
     */
    @SuppressWarnings("JavadocReference")
    void handleBattleshipException(BattleshipException e);
}
