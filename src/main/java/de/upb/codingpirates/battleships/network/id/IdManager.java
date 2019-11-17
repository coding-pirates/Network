package de.upb.codingpirates.battleships.network.id;

/**
 * Basic Interface for an {@link Id} generator. Necessary for Guice to get a class depending on distribution.
 *
 * @author Paul Becker
 */
public interface IdManager<T> {
    Id generate();
}
