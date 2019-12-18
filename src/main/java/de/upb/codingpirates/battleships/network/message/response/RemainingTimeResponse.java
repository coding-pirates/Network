package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Response to RemainingTimeRequest, which contains the remaining thinking time.
 *
 * @author Interdoc committee & Paul Becker
 */
@SuppressWarnings("unused")
public class RemainingTimeResponse extends Message {

    public static final int MESSAGE_ID = 354;

    /**
     * The time remaining in the current round
     */
    private final long time;

    public RemainingTimeResponse(long time) {
        super(MESSAGE_ID);
        this.time = time;
    }

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
