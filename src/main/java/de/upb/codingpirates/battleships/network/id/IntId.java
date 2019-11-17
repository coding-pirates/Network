package de.upb.codingpirates.battleships.network.id;

/**
 * {@link Integer} based {@link Id}
 * @author Paul Becker
 */
public class IntId implements Id {
    private Integer id;

    public IntId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntId) {
            return ((IntId) obj).id.equals(id);
        }
        return false;
    }

    @Override
    public Object getRaw() {
        return id;
    }
}
