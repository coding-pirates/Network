package de.upb.codingpirates.battleships.network.dispatcher;

import de.upb.codingpirates.battleships.network.message.Message;

public interface MessageDispatcher {

    void sendMessage(Message message);
}
