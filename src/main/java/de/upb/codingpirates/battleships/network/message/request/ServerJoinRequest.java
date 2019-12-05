package de.upb.codingpirates.battleships.network.message.request;

import com.google.gson.annotations.SerializedName;
import de.upb.codingpirates.battleships.logic.ClientType;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;

/**
 * Sent from the client to the server to log in to a server.
 *
 * @author Interdoc committee & Paul Becker
 */
public class ServerJoinRequest extends Message {

    @SuppressWarnings("WeakerAccess")
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

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public ClientType getClientType() {
        return clientType;
    }

}
