package de.upb.codingpirates.battleships.network.message.report;

import de.upb.codingpirates.battleships.network.message.Message;


public class ConnectionClosedReport extends Message {

    @SuppressWarnings("WeakerAccess")
    public static final int MESSAGE_ID = 0;

    public ConnectionClosedReport() {
        super(MESSAGE_ID);
    }
}
