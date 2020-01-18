package de.upb.codingpirates.battleships.network;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.upb.codingpirates.battleships.network.dispatcher.MessageDispatcher;
import de.upb.codingpirates.battleships.network.message.Parser;
import de.upb.codingpirates.battleships.network.message.handler.MessageHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Network application which should be created to start the creation af all needed classes (with Guice).
 * <br>
 * Use {@link NetworkApplication#getHandler()} to get the ConnectionHandlerClass you bound to the ConnectionHandler interface in the Module you used for {@link NetworkApplication#useModule(AbstractModule)} )}.
 *
 * @author Paul Becker
 */
public class NetworkApplication {

    @Nullable
    private Injector injector;

    /**
     * Creates an Guice Injector for the given AbstractModule.
     * The Module should bind a {@link Parser}, all {@link MessageHandler} and a {@link ConnectionHandler}
     *
     * @param type an Guice Abstract Module
     * @param <T>  Either a ServerModule or ClientModule
     * @return itself
     */
    @Nonnull
    public <T extends AbstractModule> NetworkApplication useModule(@Nonnull T type) {
        this.injector = Guice.createInjector(type);
        return this;
    }

    /**
     * Creates a {@link MessageDispatcher} based on the predefined AbstractModule
     */
    public void run() {
        assert injector != null;
        this.injector.getInstance(MessageDispatcher.class);
    }

    @Nullable
    public ConnectionHandler getHandler() {
        assert this.injector != null;
        return this.injector.getInstance(ConnectionHandler.class);
    }
}
