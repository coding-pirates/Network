package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.logic.util.Shot;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Sent by the client to the server to place the shots of a round.
 *
 * @author Interdoc committee & Paul Becker
 */
public class ShotsRequest extends Message {

    public static final int MESSAGE_ID = 302;

    /**
     * A collection, all shots
     * (Player and position) of a player
     * for a round contains
     */
    @Nonnull
    private final Collection<Shot> shots;

    public ShotsRequest(@Nonnull Collection<Shot> shots) {
        super(MESSAGE_ID);
        this.shots = shots;
    }

    @Nonnull
    public Collection<Shot> getShots() {
        return shots;
    }

}
