package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * It is sent by the server in response to a ServerJoinRequest.
 *
 * @author Interdoc committee, Paul Becker
 */
public class ServerJoinResponse extends Message {

    /**
     * Message id of {@link ServerJoinResponse}
     */
    public static final int MESSAGE_ID = 151;

    /**
     * Unique assigned by the server
     * ID for the client
     */
    private final int clientId;

    ServerJoinResponse(int clientId) {
        super(MESSAGE_ID);
        this.clientId = clientId;
    }

    /**
     * @return {@link #clientId}
     */
    public int getClientId() {
        return clientId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof ServerJoinResponse){
            return clientId == ((ServerJoinResponse)obj).clientId;
        }
        return false;
    }

}
