package de.upb.codingpirates.battleships.network.test;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import de.upb.codingpirates.battleships.network.Application;
import de.upb.codingpirates.battleships.network.ClientConnectionManager;
import de.upb.codingpirates.battleships.network.ConnectionManager;
import de.upb.codingpirates.battleships.network.TestMessage;
import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.id.IntId;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.network.module.ClientNetworkModule;
import de.upb.codingpirates.battleships.network.network.module.ServerNetworkModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;

/**
 * @author Paul Becker
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class NetworkTests {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void test() throws IllegalAccessException, IOException, InstantiationException {
        LOGGER.info("Setup Server Network");
        Server.main(null);
        LOGGER.info("Finish Server Network setup");

        LOGGER.info("Setup Client Network");
        Client.main(null);
        LOGGER.info("Finish Server Network setup");

        LOGGER.info("Create new ClientManager on Server");
        ClientManager manager = new ClientManager();
        LOGGER.info("Create new ClientConnector on Client");
        ClientConnector connector = new ClientConnector();

        LOGGER.info("connect Client to Server");
        connector.connect(InetAddress.getLocalHost().getHostAddress(), 11111);

        LOGGER.info("Send TestMessage to Server");
        connector.sendMessageToServer(new TestMessage());

        int i = 0;
        while (i < 600000000) {
            i++;
        }

        LOGGER.info("Send TestMessage to Client");
        manager.sendMessageToClient(new ClientEntity(1, "hello"), new TestMessage());
    }

    private static class Server {
        public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException {
            LOGGER.info("start server");

            new Application().useModule(ServerModule.class).run();
        }
    }

    private static class Client {
        public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException {
            LOGGER.info("start client");

            new Application().useModule(ClientModule.class).run();
        }
    }

    public static class ServerModule extends AbstractModule {
        @Override
        protected void configure() {
            this.install(new ServerNetworkModule());

            this.bind(ClientManager.class).toInstance(new ClientManager());
            this.bind(de.upb.codingpirates.battleships.server.handler.TestMessageHandler.class).toInstance(new de.upb.codingpirates.battleships.server.handler.TestMessageHandler());
        }
    }

    public static class ClientModule extends AbstractModule {
        @Override
        protected void configure() {
            this.install(new ClientNetworkModule());

            this.bind(ClientConnector.class).toInstance(new ClientConnector());
            this.bind(de.upb.codingpirates.battleships.client.handler.TestMessageHandler.class).toInstance(new de.upb.codingpirates.battleships.client.handler.TestMessageHandler());
        }
    }

    public static class ClientEntity {
        private final String name;
        private final int id;

        public ClientEntity(int id, String name) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }

    public static class ClientManager {
        @Inject
        private ConnectionManager connectionManager;//TODO not injected

        private Map<Id, ClientEntity> clients;

        @SuppressWarnings("SynchronizeOnNonFinalField")
        public ClientEntity create(Id id, String name) {
            synchronized (clients) {
                if (clients.containsKey(id)) {
                    return null;
                }
            }

            ClientEntity clientEntity = new ClientEntity((Integer) id.getRaw(), name);

            synchronized (clients) {
                clients.putIfAbsent(id, clientEntity);
            }
            return clientEntity;
        }

        public void sendMessageToClient(ClientEntity client, Message message) {
            try {
                this.connectionManager.send(new IntId(client.getId()), message);
            } catch (IOException e) {
                LOGGER.error("could not send message", e);
            }
        }
    }

    public static class ClientConnector {
        @Inject
        private ClientConnectionManager clientConnector;//TODO not injected

        public void connect(String host, int port) throws IOException {
            Socket socket = new Socket(host, port);
            this.clientConnector.create(socket);
        }

        public void sendMessageToServer(Message message) throws IOException {
            this.clientConnector.send(message);
        }
    }
}
