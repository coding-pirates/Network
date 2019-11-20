package de.upb.codingpirates.battleships.network;

import de.upb.codingpirates.battleships.network.exceptions.BattleshipException;

/**
 * Interface for the ConnectionHandler on Server and Client for sending messages. Necessary for Guice to get a class depending on distribution.
 *
 * @author Paul Becker
 */
public interface ConnectionHandler {

    void handleBattleshipException(BattleshipException e);
}
