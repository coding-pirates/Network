package de.upb.codingpirates.battleships.network;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Parser to {@link Parser#serialize(Message)} and {@link Parser#deserialize(String)} the messages for and from the sockets.
 * @author Paul Becker
 */
public interface Parser {

    /**
     * deserialize a JSON string to a Message Object
     *
     * @param message The JSON message
     * @return the Message Object
     */
    Message deserialize(String message);

    /**
     * serialize a Message Object to a JSON String
     * @param message The Message Object
     * @return The JSON string
     */
    String serialize(Message message);
}
