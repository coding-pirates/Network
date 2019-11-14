package de.upb.codingpirates.battleships.network.network.module;

import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
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
 * @author Paul Becker
 */
public class ServerNetworkModule extends NetworkModule {
    private static final Logger LOGGER = LogManager.getLogger(ServerNetwork.class);

    @Override
    protected void configure() {
        super.configure();
        this.bind(MessageDispatcher.class).to(ServerMessageDispatcher.class).in(Singleton.class);
    }

    @Provides
    InetSocketAddress provideServerInetSocketAddress() {
        try {
            return new InetSocketAddress(InetAddress.getLocalHost(), 11111);//TODO port
        } catch (UnknownHostException e) {
            LOGGER.error("Could not get InetSocketAddress", e);
        }
        return null;
    }

    @Provides
    Network provideServerNetwork(InetSocketAddress address, Injector injector) {
        ServerNetwork network = injector.getInstance(ServerNetwork.class);
        network.init(address);
        return network;
    }
}
