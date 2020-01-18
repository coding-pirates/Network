package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Response to RemainingTimeRequest, which contains the remaining thinking time.
 *
 * @author Interdoc committee, Paul Becker
 */
public class RemainingTimeResponse extends Message {

    /**
     * Message id of {@link RemainingTimeResponse}
     */
    public static final int MESSAGE_ID = 354;

    /**
     * The time remaining in the current round
     */
    private final long time;

    RemainingTimeResponse(long time) {
        super(MESSAGE_ID);
        this.time = time;
    }

    /**
     * @return {@link #time}
     */
    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof RemainingTimeResponse){
            return time == ((RemainingTimeResponse)obj).time;
        }
        return false;
    }

}
