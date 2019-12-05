package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * It is sent from the client to the server to get an overview of all lobbies.
 *
 * @author Interdoc committee & Paul Becker
 */
public class LobbyRequest extends Message {

    @SuppressWarnings("WeakerAccess")
    public static final int MESSAGE_ID = 201;

    public LobbyRequest() {
        super(MESSAGE_ID);
    }
}
