package de.upb.codingpirates.battleships.network.network.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import de.upb.codingpirates.battleships.network.Connection;
import de.upb.codingpirates.battleships.network.annotations.bindings.CachedThreadPool;
import de.upb.codingpirates.battleships.network.annotations.bindings.FixedThreadPool;
import de.upb.codingpirates.battleships.network.annotations.scope.ConnectionScoped;
import de.upb.codingpirates.battleships.network.id.IdManager;
import de.upb.codingpirates.battleships.network.id.IntIdManager;
import de.upb.codingpirates.battleships.network.scope.ConnectionScope;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This Network Module binds classes for all distributions to their interface. Also declares {@link com.google.inject.Provider} for ThreadPools.
 *
 * @author Paul Becker
 */
@SuppressWarnings("WeakerAccess")
public class NetworkModule extends AbstractModule {
    @Override
    protected void configure() {
        this.bind(IdManager.class).to(IntIdManager.class);
        ConnectionScope connectionScope = new ConnectionScope();
        this.bindScope(ConnectionScoped.class, connectionScope);
        this.bind(ConnectionScope.class).toInstance(connectionScope);
        this.bind(Connection.class).toProvider(() -> {
            throw new IllegalStateException("Cannot load Client instance which is only available in connection scope.");
        }).in(ConnectionScoped.class);
    }

    /**
     * @return A ThreadPool with just one Thread.
     */
    @Provides
    @FixedThreadPool(size = 1)
    protected ExecutorService provideFixedThreadPool() {
        return Executors.newFixedThreadPool(1);
    }

    /**
     * @return new CachedThreadPool.
     */
    @Provides
    @CachedThreadPool
    protected ExecutorService provideCachedThreadPool() {
        return Executors.newCachedThreadPool();
    }
}
