package de.upb.codingpirates.battleships.network.network.module;

import com.google.inject.Singleton;
import de.upb.codingpirates.battleships.network.connectionmanager.ClientConnectionManager;
import de.upb.codingpirates.battleships.network.dispatcher.ClientMessageDispatcher;
import de.upb.codingpirates.battleships.network.dispatcher.MessageDispatcher;
import de.upb.codingpirates.battleships.network.network.ClientNetwork;
import de.upb.codingpirates.battleships.network.network.Network;

/**
 * This Network Module binds classes to their interface, just for the client. Also declares {@link com.google.inject.Provider} for the ServerNetwork.
 *
 * @author Paul Becker
 */
public class ClientNetworkModule extends NetworkModule {
    @Override
    protected void configure() {
        super.configure();

        this.bind(ClientConnectionManager.class).in(Singleton.class);
        this.bind(MessageDispatcher.class).to(ClientMessageDispatcher.class).in(Singleton.class);
        this.bind(Network.class).to(ClientNetwork.class);
    }
}
