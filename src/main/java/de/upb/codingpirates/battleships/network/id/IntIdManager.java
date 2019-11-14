package de.upb.codingpirates.battleships.network.id;

/**
 * @author Paul Becker
 */
public class IntIdManager extends IdManager<Integer> {
    private Integer counter = 0;

    @Override
    public Id generate() {
        //noinspection SynchronizeOnNonFinalField
        synchronized (this.counter) {
            return new IntId(counter++);
        }
    }
}
