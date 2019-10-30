package de.upb.codingpirates.battleships.network.message;

import de.upb.codingpirates.battleships.network.dto.ClientKind;

public final class ServerJoinRequest extends Message {

    private String name;

    private ClientKind clientKind;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ClientKind getClientKind() {
        return clientKind;
    }

    public void setClientKind(final ClientKind clientKind) {
        this.clientKind = clientKind;
    }
}
