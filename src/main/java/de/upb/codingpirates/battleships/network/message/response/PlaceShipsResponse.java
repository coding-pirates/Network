package de.upb.codingpirates.battleships.network.message.response;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Sent by the server to the client when ship placement is accepted.
 *
 * @author Interdoc committee & Paul Becker
 */
public class PlaceShipsResponse extends Message {

    @SuppressWarnings("WeakerAccess")
    public static final int MESSAGE_ID = 351;

    public PlaceShipsResponse() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof PlaceShipsResponse;
    }
}
