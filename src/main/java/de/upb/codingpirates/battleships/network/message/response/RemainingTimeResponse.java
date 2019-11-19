package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Response to RemainingTimeRequest, which contains the remaining thinking time.
 *
 * @author Interdoc committee & Paul Becker
 */
public class RemainingTimeResponse extends Message {

    public static final int MESSAGE_ID = 354;

    /**
     * Die in der aktuellen Runde
     * noch verbleibende Bedenkzeit
     */
    private final long time;

    public RemainingTimeResponse(long time) {
        super(MESSAGE_ID);
        this.time = time;
    }

    public long getTime() {
        return time;
    }

}
