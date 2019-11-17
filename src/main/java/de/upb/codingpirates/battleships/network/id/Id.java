package de.upb.codingpirates.battleships.network.id;

/**
 * Id for identification of a client. Necessary for Guice to get a class depending on distribution.
 *
 * @author Paul Becker
 */
public interface Id {
    Object getRaw();
}
