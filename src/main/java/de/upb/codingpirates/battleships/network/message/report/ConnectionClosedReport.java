package de.upb.codingpirates.battleships.network.message.report;

import de.upb.codingpirates.battleships.network.message.Message;

public class ConnectionClosedReport extends Message {

    public static final int MESSAGE_ID = 000;

    public ConnectionClosedReport() {
        super(MESSAGE_ID);
    }
}
