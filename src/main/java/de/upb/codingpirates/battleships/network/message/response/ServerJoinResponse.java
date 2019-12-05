package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;

/**
 * It is sent by the server in response to a ServerJoinRequest.
 *
 * @author Interdoc committee & Paul Becker
 */
public class ServerJoinResponse extends Message {

    @SuppressWarnings("WeakerAccess")
    public static final int MESSAGE_ID = 151;

    /**
     * Unique assigned by the server
     * ID for the client
     */
    private final int clientId;

    public ServerJoinResponse(@Nonnull Integer clientId) {
        super(MESSAGE_ID);
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

}
