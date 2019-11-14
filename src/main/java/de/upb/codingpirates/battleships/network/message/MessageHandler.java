package de.upb.codingpirates.battleships.network.message;

/**
 * Name of the Class must begin with the message's name and ends with "Handler".
 * The Handlers must be in the package {@code "de.upb.codingpirates.battleships.'client/server'.handlers"}
 *
 * @param <T>
 * @author Paul Becker
 */
public interface MessageHandler<T extends Message> {

    void handle(Message message);

    boolean canHandle(Message message);
}
