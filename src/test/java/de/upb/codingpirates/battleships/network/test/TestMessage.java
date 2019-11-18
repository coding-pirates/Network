package de.upb.codingpirates.battleships.network.test;

import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.id.IntId;
import de.upb.codingpirates.battleships.network.message.Message;

/**
 * @author Paul Becker
 */
public class TestMessage implements Message {//TODO remove
    @Override
    public Id getId() {
        return new IntId(0);
    }
}
