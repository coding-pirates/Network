package de.upb.codingpirates.battleships.network.message.report;

import de.upb.codingpirates.battleships.network.message.Message;

/**
 * Informs the server about a lost connection
 */
public class ConnectionClosedReport extends Message {

    /**
     * Message id of {@link ConnectionClosedReport}
     */
    public static final int MESSAGE_ID = 0;

    ConnectionClosedReport() {
        super(MESSAGE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        return obj instanceof ConnectionClosedReport;
    }
}
