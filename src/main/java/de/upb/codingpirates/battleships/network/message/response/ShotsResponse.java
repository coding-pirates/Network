package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Dispatched by the server with correctly positioned shots in response to ShotsRequest.
 *
 * @author Interdoc committee & Paul Becker
 */
public class ShotsResponse extends Message {

    public static final int MESSAGE_ID = 352;

    public ShotsResponse() {
        super(MESSAGE_ID);
    }
}
