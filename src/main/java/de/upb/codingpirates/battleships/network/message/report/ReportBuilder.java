package de.upb.codingpirates.battleships.network.message.report;


import javax.annotation.Nonnull;

public final class ReportBuilder {
    private ReportBuilder() {}

    private static final ConnectionClosedReport connectionClosedReport = new ConnectionClosedReport();

    @Nonnull
    public static ConnectionClosedReport connectionClosedReport(){
        return connectionClosedReport;
    }
}
