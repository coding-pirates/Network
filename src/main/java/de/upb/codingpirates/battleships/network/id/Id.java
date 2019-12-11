package de.upb.codingpirates.battleships.network.id;

import com.google.common.base.Preconditions;

/**
 * {@link Integer} based {@link Id}
 *
 * @author Paul Becker
 */
public class Id {
    private final Integer id;

    /**
     * @param id should be greater than 0
     */
    public Id(Integer id) {
        Preconditions.checkArgument(id >= 0);

        this.id = id;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Id) {
            return ((Id) obj).id.equals(id);
        }
        return false;
    }

    public Integer getInt() {
        return id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
