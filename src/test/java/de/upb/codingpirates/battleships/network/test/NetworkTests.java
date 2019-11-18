package de.upb.codingpirates.battleships.network.test;

import com.google.common.collect.Maps;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.upb.codingpirates.battleships.network.ConnectionHandler;
import de.upb.codingpirates.battleships.network.NetworkApplication;
import de.upb.codingpirates.battleships.network.Parser;
import de.upb.codingpirates.battleships.network.Properties;
import de.upb.codingpirates.battleships.network.connectionmanager.ClientConnectionManager;
import de.upb.codingpirates.battleships.network.connectionmanager.ServerConnectionManager;
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
        NetworkApplication server = Server.main();

        LOGGER.info("Setup Client Network 1");
        NetworkApplication client = Client.main();

        LOGGER.info("Setup Client Network 2");
        NetworkApplication client2 = Client.main();

        LOGGER.info("Create new ClientManager on Server");
        ClientManager manager = (ClientManager) server.getHandler();
        LOGGER.info("Create new ClientConnector on Client 1");
        ClientConnector connector = (ClientConnector) client.getHandler();
        LOGGER.info("Create new ClientConnector on Client 2");
        ClientConnector connector2 = (ClientConnector) client2.getHandler();

        LOGGER.info("connect Client 1 to Server");
        connector.connect(InetAddress.getLocalHost().getHostAddress(), Properties.PORT);
        LOGGER.info("connect Client 2 to Server");
        connector2.connect(InetAddress.getLocalHost().getHostAddress(), Properties.PORT);

        LOGGER.info("Send TestMessage 1 to Server");
        connector.sendMessageToServer(new TestMessage());
        LOGGER.info("Send TestMessage 2 to Server");
        connector2.sendMessageToServer(new TestMessage());

        int i = 0;
        while (i < 6000000) {
            i++;
        }

        LOGGER.info("Send TestMessage to Client");
        manager.sendMessageToAll(new TestMessage());

        i = 0;
        while (i < 6000000) {
            i++;
        }
    }

    private static class Server {
        public static NetworkApplication main() throws IllegalAccessException, IOException, InstantiationException {
            LOGGER.info("Start server network module");

            NetworkApplication application = new NetworkApplication();
            application.useModule(ServerModule.class).run();
            return application;
        }
    }

    private static class Client {
        public static NetworkApplication main() throws IllegalAccessException, IOException, InstantiationException {
            LOGGER.info("Start client network module");

            NetworkApplication application = new NetworkApplication();
            application.useModule(ClientModule.class).run();
            return application;
        }
    }

    public static class ServerModule extends AbstractModule {
        @Override
        protected void configure() {
            this.install(new ServerNetworkModule());

            this.bind(ConnectionHandler.class).to(ClientManager.class).in(Singleton.class);
            this.bind(de.upb.codingpirates.battleships.server.handler.TestMessageHandler.class);
            this.bind(Parser.class).toInstance(new TestParser());
        }
    }

    public static class ClientModule extends AbstractModule {
        @Override
        protected void configure() {
            this.install(new ClientNetworkModule());

            this.bind(ConnectionHandler.class).toInstance(new ClientConnector());
            this.bind(de.upb.codingpirates.battleships.client.handler.TestMessageHandler.class);
            this.bind(Parser.class).toInstance(new TestParser());
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

    public static class ClientManager implements ConnectionHandler {
        @Inject
        private ServerConnectionManager connectionManager;

        private final Map<Id, ClientEntity> clients = Maps.newHashMap();

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

        public void sendMessageToAll(Message message) {
            try {
                for (Id id : clients.keySet()) {
                    this.connectionManager.send(id, message);
                    LOGGER.debug("send message to {}", clients.get(id).name);
                }
            } catch (IOException e) {
                LOGGER.error("could not send message", e);
            }
        }
    }

    public static class ClientConnector implements ConnectionHandler {
        @Inject
        private ClientConnectionManager clientConnector;

        public void connect(String host, int port) throws IOException {
            this.clientConnector.create(host, port);
        }

        public void sendMessageToServer(Message message) throws IOException {
            this.clientConnector.send(message);
        }

        public void disconnect() throws IOException {
            this.clientConnector.disconnect();
        }
    }


}
