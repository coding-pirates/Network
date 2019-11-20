package de.upb.codingpirates.battleships.network.message;

import com.google.gson.*;
import de.upb.codingpirates.battleships.network.exceptions.parser.BadJsonException;
import de.upb.codingpirates.battleships.network.exceptions.parser.BadMessageException;
import de.upb.codingpirates.battleships.network.exceptions.parser.ParserException;
import de.upb.codingpirates.battleships.network.message.notification.*;
import de.upb.codingpirates.battleships.network.message.request.*;
import de.upb.codingpirates.battleships.network.message.response.*;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * Parser to {@link Parser#serialize(Message)} and {@link Parser#deserialize(String)} the messages for and from the sockets.
 *
 * @author Paul Becker & Interdock committee
 */
public class Parser {

    private static final Map<Integer, Class<? extends Message>> messageRelations = new HashMap<Integer, Class<? extends Message>>() {
        {
            put(361, PauseNotification.class);
            put(362, ContinueNotification.class);
            put(363, GameStartNotification.class);
            put(364, FinishNotification.class);
            put(365, RoundStartNotification.class);
            put(366, GameInitNotification.class);
            put(367, LeaveNotification.class);
            put(368, SpectatorUpdateNotification.class);
            put(369, PlayerUpdateNotification.class);

            put(999, ErrorNotification.class);

            put(202, GameJoinPlayerRequest.class);
            put(252, GameJoinPlayerResponse.class);

            put(203, GameJoinSpectatorRequest.class);
            put(253, GameJoinSpectatorResponse.class);

            put(301, PlaceShipsRequest.class);
            put(351, PlaceShipsResponse.class);

            put(302, ShotsRequest.class);
            put(352, ShotsResponse.class);

            put(303, PointsRequest.class);
            put(353, PointsResponse.class);

            put(304, RemainingTimeRequest.class);
            put(354, RemainingTimeResponse.class);

            put(305, PlayerGameStateRequest.class);
            put(355, PlayerGameStateResponse.class);

            put(306, SpectatorGameStateRequest.class);
            put(356, SpectatorGameStateResponse.class);

            put(101, ServerJoinRequest.class);
            put(151, ServerJoinResponse.class);

            put(201, LobbyRequest.class);
            put(251, LobbyResponse.class);
        }
    };
    private final Gson gson = new Gson();

    public static void addMessage(int id, Class<? extends Message> message) {
        messageRelations.putIfAbsent(id, message);
    }

    /**
     * deserialize a JSON string to a Message Object
     *
     * @param message The JSON message
     * @return the Message Object
     */
    public Message deserialize(@Nonnull String message) throws ParserException {
        JsonElement jsonElement;
        try {
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(message).getAsJsonObject();
            jsonElement = obj.get("messageId");
            if (jsonElement == null) {
                throw new BadJsonException("No messageId found");
            }
        } catch (JsonSyntaxException e) {
            throw new BadJsonException(e.getMessage());
        }

        int msgId = jsonElement.getAsInt();
        Class<? extends Message> messageClass = messageRelations.get(msgId);
        if (messageClass == null) {
            throw new BadMessageException("could not find message with id: " + msgId);
        }
        try {
            return gson.fromJson(message, messageClass);
        } catch (JsonSyntaxException e) {
            throw new BadMessageException(e.getMessage());
        }

    }

    /**
     * serialize a Message Object to a JSON String
     *
     * @param message The Message Object
     * @return The JSON string
     */
    public String serialize(@Nonnull Message message) {
        if (messageRelations.containsKey(message.messageId))
            return gson.toJson(message);
        else
            throw new IllegalArgumentException("the message is not registered. MessageId: " + message.messageId);
    }
}
