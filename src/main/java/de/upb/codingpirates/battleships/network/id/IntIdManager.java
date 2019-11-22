package de.upb.codingpirates.battleships.network.id;

import javax.annotation.Nonnull;

/**
 * {@link IntId} generator.
 *
 * @author Paul Becker
 */
public class IntIdManager implements IdManager<Integer> {
    private Integer counter = 0;

    @SuppressWarnings("SynchronizeOnNonFinalField")
    @Override
    public @Nonnull
    Id generate() {
        synchronized (this.counter) {
            return new IntId(counter++);
        }
    }
}
