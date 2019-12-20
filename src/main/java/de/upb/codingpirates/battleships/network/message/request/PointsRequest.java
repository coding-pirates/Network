package de.upb.codingpirates.battleships.network.message.request;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Can be sent from the client to the server to get the current point list.
 *
 * @author Interdoc committee, Paul Becker
 */
public class PointsRequest extends Message {

    /**
     * Message id of {@link PointsRequest}
     */
    public static final int MESSAGE_ID = 303;

    PointsRequest() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof PointsRequest;
    }
}
