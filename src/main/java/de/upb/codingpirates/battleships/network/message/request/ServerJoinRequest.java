package de.upb.codingpirates.battleships.network.message.request;

import com.google.gson.annotations.SerializedName;
import de.upb.codingpirates.battleships.logic.ClientType;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;

/**
 * Sent from the client to the server to log in to a server.
 *
 * @author Interdoc committee, Paul Becker
 */
public class ServerJoinRequest extends Message {

    /**
     * Message id of {@link ServerJoinRequest}
     */
    public static final int MESSAGE_ID = 101;

    /**
     * Freely selectable player name
     */
    @Nonnull
    private final String name;
    /**
     * Type of participant
     */
    @Nonnull
    @SerializedName("clientKind")
    private final ClientType clientType;

    public ServerJoinRequest(@Nonnull String name, @Nonnull ClientType clientType) {
        super(MESSAGE_ID);
        this.name = name;
        this.clientType = clientType;
    }

    /**
     * @return {@link #name}
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * @return {@link #clientType}
     */
    @Nonnull
    public ClientType getClientType() {
        return clientType;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ServerJoinRequest){
            return name.equals(((ServerJoinRequest) obj).name) && clientType == ((ServerJoinRequest)obj).clientType;
        }
        return false;
    }
}
