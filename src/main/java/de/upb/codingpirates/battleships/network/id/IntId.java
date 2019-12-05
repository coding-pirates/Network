package de.upb.codingpirates.battleships.network.id;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;

/**
 * {@link Integer} based {@link Id}
 *
 * @author Paul Becker
 */
public class IntId implements Id {
    private final Integer id;

    /**
     * @param id should be greater than 0
     */
    public IntId(Integer id) {
        Preconditions.checkArgument(id >= 0);

        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntId) {
            return ((IntId) obj).id.equals(id);
        }
        return false;
    }

    @Nonnull
    @Override
    public Object getRaw() {
        return id;
    }

    @SuppressWarnings("unused")
    @Override
    public Integer getInt() {
        return id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
