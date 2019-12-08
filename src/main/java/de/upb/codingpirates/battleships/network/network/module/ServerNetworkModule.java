package de.upb.codingpirates.battleships.network.network.module;

import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import de.upb.codingpirates.battleships.network.Properties;
import de.upb.codingpirates.battleships.network.connectionmanager.ServerConnectionManager;
import de.upb.codingpirates.battleships.network.dispatcher.MessageDispatcher;
import de.upb.codingpirates.battleships.network.dispatcher.ServerMessageDispatcher;
import de.upb.codingpirates.battleships.network.network.Network;
import de.upb.codingpirates.battleships.network.network.ServerNetwork;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * This Network Module binds classes to their interface, just for the server. Also declares {@link com.google.inject.Provider} for the {@link ServerNetwork} and a {@link InetSocketAddress} for the local ip
 *
 * @author Paul Becker
 */
public class ServerNetworkModule extends NetworkModule {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void configure() {
        super.configure();

        this.bind(ServerConnectionManager.class).in(Singleton.class);
        this.bind(MessageDispatcher.class).to(ServerMessageDispatcher.class).in(Singleton.class);
    }

    /**
     * @return {@link InetSocketAddress} for the local ip
     */
    @Provides
    InetSocketAddress provideServerInetSocketAddress() {
        try {
            return new InetSocketAddress(InetAddress.getLocalHost(), Properties.PORT);
        } catch (UnknownHostException e) {
            LOGGER.info("Could not get InetSocketAddress", e);
        }
        return null;
    }

    /**
     * @return {@link ServerNetwork}
     */
    @Provides
    Network provideServerNetwork(InetSocketAddress address, Injector injector) {
        return injector.getInstance(ServerNetwork.class);
    }
}
