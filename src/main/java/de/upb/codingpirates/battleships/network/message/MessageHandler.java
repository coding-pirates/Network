package de.upb.codingpirates.battleships.network.message;

/**
 * Basic Interface for all MessageHandler
 *
 * Name of the Class must begin with the message's name and ends with "Handler".
 * The Handlers must be in the package {@code "de.upb.codingpirates.battleships.'client/server'.handler"}
 *
 * @param <T>
 * @author Paul Becker
 */
public interface MessageHandler<T extends Message> {

    void handle(Message message);

    boolean canHandle(Message message);
}
