package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent by the observer to the server to get the full score.
 *
 * @author Interdoc committee & Paul Becker
 */
public class SpectatorGameStateRequest extends Message {


    public static final int MESSAGE_ID = 306;

    public SpectatorGameStateRequest() {
        super(MESSAGE_ID);
    }


}
