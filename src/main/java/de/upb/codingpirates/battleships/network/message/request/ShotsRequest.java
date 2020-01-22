package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.logic.Shot;
import de.upb.codingpirates.battleships.network.message.Message;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Sent by the client to the server to place the shots of a round.
 *
 * @author Interdoc committee, Paul Becker
 */
public class ShotsRequest extends Message {

    /**
     * Message id of {@link ShotsRequest}
     */
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

    /**
     * @return {@link #shots}
     */
    @Nonnull
    public Collection<Shot> getShots() {
        return shots;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof ShotsRequest){
            return shots.equals(((ShotsRequest)obj).shots);
        }
        return false;
    }

}
