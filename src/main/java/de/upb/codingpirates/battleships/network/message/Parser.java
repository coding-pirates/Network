package de.upb.codingpirates.battleships.network.message;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import com.google.gson.*;

import de.upb.codingpirates.battleships.network.exceptions.parser.BadJsonException;
import de.upb.codingpirates.battleships.network.exceptions.parser.BadMessageException;
import de.upb.codingpirates.battleships.network.exceptions.parser.ParserException;
import de.upb.codingpirates.battleships.network.message.notification.*;
import de.upb.codingpirates.battleships.network.message.request.*;
import de.upb.codingpirates.battleships.network.message.response.*;

/**
 * Parser to {@link Parser#serialize(Message)} and {@link Parser#deserialize(String)} the messages for and from the
 * sockets.
 *
 * @author Interdoc committee
 * @author Paul Becker
 * @author Andre Blanke
 */
public final class Parser {

    private static final Map<Integer, Class<? extends Message>> messageRelations = new HashMap<>();

    static {
        messageRelations.put(361, PauseNotification.class);
        messageRelations.put(362, ContinueNotification.class);
        messageRelations.put(363, GameStartNotification.class);
        messageRelations.put(364, FinishNotification.class);
        messageRelations.put(365, RoundStartNotification.class);
        messageRelations.put(366, GameInitNotification.class);
        messageRelations.put(367, LeaveNotification.class);
        messageRelations.put(368, SpectatorUpdateNotification.class);
        messageRelations.put(369, PlayerUpdateNotification.class);

        messageRelations.put(999, ErrorNotification.class);

        messageRelations.put(202, GameJoinPlayerRequest.class);
        messageRelations.put(252, GameJoinPlayerResponse.class);

        messageRelations.put(203, GameJoinSpectatorRequest.class);
        messageRelations.put(253, GameJoinSpectatorResponse.class);

        messageRelations.put(301, PlaceShipsRequest.class);
        messageRelations.put(351, PlaceShipsResponse.class);

        messageRelations.put(302, ShotsRequest.class);
        messageRelations.put(352, ShotsResponse.class);

        messageRelations.put(303, PointsRequest.class);
        messageRelations.put(353, PointsResponse.class);

        messageRelations.put(304, RemainingTimeRequest.class);
        messageRelations.put(354, RemainingTimeResponse.class);

        messageRelations.put(305, PlayerGameStateRequest.class);
        messageRelations.put(355, PlayerGameStateResponse.class);

        messageRelations.put(306, SpectatorGameStateRequest.class);
        messageRelations.put(356, SpectatorGameStateResponse.class);

        messageRelations.put(101, ServerJoinRequest.class);
        messageRelations.put(151, ServerJoinResponse.class);

        messageRelations.put(201, LobbyRequest.class);
        messageRelations.put(251, LobbyResponse.class);
    }

    private final Gson gson = new Gson();

    public static void addMessage(final int id, final Class<? extends Message> message) {
        messageRelations.putIfAbsent(id, message);
    }

    /**
     * Deserializes a JSON string to a {@link Message} object.
     *
     * @param message The JSON representation of a {@link Message} object which is to be deserialized.
     *
     * @return The deserialized {@link Message} object.
     */
    public Message deserialize(@Nonnull String message) throws ParserException {
        final JsonElement messageIdElement;

        try {
            message = message.replaceAll("\\r\\n", "");

            final JsonObject object = new JsonParser().parse(message).getAsJsonObject();

            messageIdElement = object.get("messageId");
            if (messageIdElement == null)
                throw new BadJsonException("No messageId found.");
        } catch (final JsonSyntaxException exception) {
            throw new BadJsonException(exception.getMessage());
        }

        final int messageId = messageIdElement.getAsInt();

        final Class<? extends Message> messageClass = messageRelations.get(messageId);
        if (messageClass == null)
            throw new BadMessageException(String.format("Could not find Message with id '%d'.", messageId));

        try {
            return gson.fromJson(message, messageClass);
        } catch (final JsonSyntaxException exception) {
            throw new BadMessageException(exception.getMessage());
        }
    }

    /**
     * Serializes a {@link Message} object to a JSON string.
     *
     * @param message The {@link Message} object which is to be serialized to a JSON string.
     *
     * @return The JSON string representation of the provided {@code message}.
     */
    public String serialize(@Nonnull final Message message) {
        if (messageRelations.containsKey(message.getMessageId()))
            return gson.toJson(message);
        throw new IllegalArgumentException("the message is not registered. MessageId: " + message.getMessageId());
    }
}
