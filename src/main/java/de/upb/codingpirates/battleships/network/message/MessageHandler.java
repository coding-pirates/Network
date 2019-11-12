package de.upb.codingpirates.battleships.network.message;

public interface MessageHandler<T extends Message> {

    void handle(T message);
}
