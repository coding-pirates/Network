package de.upb.codingpirates.battleships.network;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.upb.codingpirates.battleships.network.dispatcher.MessageDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * Network application which should be created to start the creation af all needed classes (with Guice).
 * <p></p>
 * Use {@link NetworkApplication#getHandler()} to get the ConnectionHandlerClass you bound to the ConnectionHandler interface in the Module you used for {@link NetworkApplication#useModule(Class)}.
 *
 * @author Paul Becker
 */
public class NetworkApplication {
    private static final Logger LOGGER = LogManager.getLogger();

    private @Nullable
    Injector injector;

    /**
     * Creates an Guice Injector for the given AbstractModule
     *
     * @param type an Guice Abstract Module
     * @param <T>  Either a ServerModule or ClientModule
     * @return itself
     */
    public @Nullable
    <T extends AbstractModule> NetworkApplication useModule(Class<T> type) throws IllegalAccessException, InstantiationException, IOException {
        AbstractModule module = type.newInstance();
        if (module == null) {
            LOGGER.error("Could not use Module {}", type);
            return null;
        }
        this.injector = Guice.createInjector(module);
        return this;
    }

    /**
     * Creates a {@link MessageDispatcher} based on the predefined AbstractModule
     */
    public void run() {
        if (this.injector == null)
            LOGGER.error("The injector is not set up. Please use a Module first");
        else
            this.injector.getInstance(MessageDispatcher.class);
    }

    public @Nullable
    ConnectionHandler getHandler() {
        if (this.injector == null) {
            LOGGER.error("The injector is not set up. Please use a Module first");
            return null;
        } else
            return this.injector.getInstance(ConnectionHandler.class);
    }
}
