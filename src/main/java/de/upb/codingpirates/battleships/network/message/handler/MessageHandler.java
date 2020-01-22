package de.upb.codingpirates.battleships.network.message.handler;

import de.upb.codingpirates.battleships.network.exceptions.game.GameException;
import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * Basic Interface for all MessageHandler
 * <p>
 * Name of the Class must begin with the message's name and ends with "Handler".
 * The Handlers must be in the package {@code "de.upb.codingpirates.battleships.'client/server'.handler"}
 *
 * @param <T> MessageType of the MessageHandler
 * @author Paul Becker
 */
public interface MessageHandler<T extends Message> {

    /**
     * @param message      the message which should be handled
     * @param connectionId own client id on the server
     * @throws GameException      if the handling of the Message throws a {@link GameException} which should be sent to the client
     * @throws ClassCastException if can {@link #canHandle(Message)} returned true for a class not instance of {@link T}
     */
    void handle(@Nonnull T message, @Nonnull Id connectionId) throws GameException, ClassCastException, IOException;

    /**
     * should check if message is instance of the generic MessageClass {@link T}
     *
     * @param message the message which should be checked
     * @return if the message can be handled
     */
    boolean canHandle(@Nonnull Message message);
}
