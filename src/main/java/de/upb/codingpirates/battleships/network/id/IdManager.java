package de.upb.codingpirates.battleships.network.id;

import javax.annotation.Nonnull;

/**
 * {@link Id} generator.
 *
 * @author Paul Becker
 */
public class IdManager {
    private Integer counter = 0;

    @SuppressWarnings("SynchronizeOnNonFinalField")
    @Nonnull
    public Id generate() {
        synchronized (this.counter) {
            return new Id(counter++);
        }
    }
}
