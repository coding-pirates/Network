package de.upb.codingpirates.battleships.network.network.module;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import de.upb.codingpirates.battleships.network.connectionmanager.ClientConnectionManager;
import de.upb.codingpirates.battleships.network.dispatcher.ClientMessageDispatcher;
import de.upb.codingpirates.battleships.network.dispatcher.MessageDispatcher;
import de.upb.codingpirates.battleships.network.network.ClientNetwork;
import de.upb.codingpirates.battleships.network.network.Network;

/**
 * @author Paul Becker
 */
public class ClientNetworkModule extends NetworkModule {
    @Override
    protected void configure() {
        super.configure();
        this.bind(MessageDispatcher.class).to(ClientMessageDispatcher.class);

        this.bind(ClientConnectionManager.class).in(Singleton.class);
    }

    @Provides
    Network provideClientNetwork() {
        return new ClientNetwork();
    }
}
