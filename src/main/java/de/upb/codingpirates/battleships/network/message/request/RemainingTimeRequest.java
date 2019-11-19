package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Can be sent from the client to the server for the remaining time to think
 * ask.
 *
 * @author Interdoc committee & Paul Becker
 */
public class RemainingTimeRequest extends Message {

    public static final int MESSAGE_ID = 304;

    public RemainingTimeRequest() {
        super(MESSAGE_ID);
    }


}
