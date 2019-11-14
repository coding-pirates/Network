package de.upb.codingpirates.battleships.network;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.upb.codingpirates.battleships.network.dispatcher.MessageDispatcher;

import java.io.IOException;

/**
 * @author Paul Becker
 */
public class Application {
    private Injector injector;

    public <T extends AbstractModule> Application useModule(Class<T> type) throws IllegalAccessException, InstantiationException, IOException {
        AbstractModule module = type.newInstance();
        if (module == null) {
            throw new IOException();
        }
        this.setUpInjector(module);
        return this;
    }

    private void setUpInjector(AbstractModule module) {
        this.injector = Guice.createInjector(module);
    }

    public void run() {
        this.injector.getInstance(MessageDispatcher.class);
    }

}
