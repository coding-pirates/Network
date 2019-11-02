package de.upb.codingpirates.battleships.network.message;

public final class ServerJoinResponse extends Message {

    private int clientId;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(final int clientId) {
        this.clientId = clientId;
    }
}
