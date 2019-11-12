package de.upb.codingpirates.battleships.network.message;

public interface MessageHandleManager {

    <T extends Message> void registerMessageHandler(Class<T> clazz, MessageHandler<T> handler);
}
