package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * It is sent from the client to the server to get an overview of all lobbies.
 *
 * @author Interdoc committee, Paul Becker
 */
public class LobbyRequest extends Message {

    /**
     * Message id of {@link LobbyRequest}
     */
    public static final int MESSAGE_ID = 201;

    public LobbyRequest() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof LobbyRequest;
    }
}
