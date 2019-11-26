package de.upb.codingpirates.battleships.network;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.upb.codingpirates.battleships.network.dispatcher.MessageDispatcher;
import de.upb.codingpirates.battleships.network.message.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Network application which should be created to start the creation af all needed classes (with Guice).
 * <p></p>
 * Use {@link NetworkApplication#getHandler()} to get the ConnectionHandlerClass you bound to the ConnectionHandler interface in the Module you used for {@link NetworkApplication#useModule(Class)}.
 *
 * @author Paul Becker
 */
public class NetworkApplication {
   // private static final Logger LOGGER = LogManager.getLogger();

    @Nullable
    protected Injector injector;

    /**
     * Creates an Guice Injector for the given AbstractModule.
     * The Module should bind a {@link Parser}, all {@link de.upb.codingpirates.battleships.network.message.MessageHandler} and a {@link ConnectionHandler}
     *
     * @param type an Guice Abstract Module
     * @param <T>  Either a ServerModule or ClientModule
     * @return itself
     */
    @Nullable
    public <T extends AbstractModule> NetworkApplication useModule(@Nonnull Class<T> type) throws IllegalAccessException, InstantiationException {
        AbstractModule module = type.newInstance();
        if (module == null) {
           // LOGGER.error("Could not use Module {}", type);
            return null;
        }
        this.injector = Guice.createInjector(module);
        return this;
    }

    /**
     * Creates a {@link MessageDispatcher} based on the predefined AbstractModule
     */
    public void run() {
        if (this.injector == null){}
           // LOGGER.error("The injector is not set up. Please use a Module first");
        else
            this.injector.getInstance(MessageDispatcher.class);
    }

    @Nullable
    public ConnectionHandler getHandler() {
        if (this.injector == null) {
            //LOGGER.error("The injector is not set up. Please use a Module first");
            return null;
        } else
            return this.injector.getInstance(ConnectionHandler.class);
    }
}
