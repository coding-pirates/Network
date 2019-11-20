package de.upb.codingpirates.battleships.network.message;

/**
 * Basic Interface for all MessageHandler
 * <p>
 * Name of the Class must begin with the message's name and ends with "Handler".
 * The Handlers must be in the package {@code "de.upb.codingpirates.battleships.'client/server'.handler"}
 *
 * @param <T>
 * @author Paul Becker
 */
public interface MessageHandler<T extends Message> {

    /**
     * @throws ClassCastException if can {@link #canHandle(Message)} returned true for a class not instance of {@link T}
     */
    void handle(T message);

    /**
     * should check if message is instance of the generic MessageClass {@link T}
     */
    boolean canHandle(Message message);
}
