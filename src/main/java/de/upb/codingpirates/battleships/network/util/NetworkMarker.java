package de.upb.codingpirates.battleships.network.util;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class NetworkMarker {

    public static final Marker NETWORK = MarkerManager.getMarker("Network");
    public static final Marker CONNECTION = MarkerManager.getMarker("Connection");
    public static final Marker MESSAGE = MarkerManager.getMarker("Message");
}
