package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Dispatched by the server with correctly positioned shots in response to ShotsRequest.
 *
 * @author Interdoc committee, Paul Becker
 */
public class ShotsResponse extends Message {

    /**
     * Message id of {@link ShotsResponse}
     */
    public static final int MESSAGE_ID = 352;

    ShotsResponse() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof ShotsResponse;
    }
}
