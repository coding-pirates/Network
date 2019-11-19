package de.upb.codingpirates.battleships.network.message.notification;

import de.upb.codingpirates.battleships.logic.util.Client;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Is sent from the server to the client to notify the client that now all players
 * joined and the placement of the ships can be started.
 *
 * @author Interdoc committee & Paul Becker
 */
public class GameInitNotification extends Message {

    public static final int MESSAGE_ID = 366;

    /**
     * A collection about client,
     * the all participating clients of the
     * Game contains
     */
    @Nonnull
    private final Collection<Client> clientList;


    public GameInitNotification(@Nonnull Collection<Client> clientList) {
        super(MESSAGE_ID);
        this.clientList = clientList;
    }

    @Nonnull
    public Collection<Client> getClientList() {
        return clientList;
    }

}
