package de.upb.codingpirates.battleships.network;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * @author Paul Becker
 */
public interface Parser {

    Message deserialize(String message);

    String serialize(Message message);
}
