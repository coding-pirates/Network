package de.upb.codingpirates.battleships.network;

import com.google.common.base.Preconditions;
import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.message.Message;
import de.upb.codingpirates.battleships.network.message.Parser;

import javax.annotation.Nonnull;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * A new Object of {@link Connection} is created on connection from server to client and client to server.
 * <p></p>
 * This Class handles the direct read & write access for the sockets.
 *
 * @author Paul Becker
 */
public class Connection {

    private @Nonnull
    final Id id;
    private @Nonnull
    final Socket socket;
    private @Nonnull
    final Parser parser;
    private @Nonnull
    final BufferedReader reader;
    private @Nonnull
    final BufferedWriter writer;

    public Connection(@Nonnull Id id, @Nonnull Socket socket) throws IOException {
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(socket);

        this.id = id;
        this.socket = socket;
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        this.parser = new Parser();
    }

    /**
     * Parses a {@link Message} to string and sends is through the socket.
     *
     * @param message The message to parse
     * @throws IOException
     */
    public void send(@Nonnull Message message) throws IOException {
        this.send(this.parser.serialize(message));
    }

    /**
     * Reads from socket and parses a string to {@link Message}.
     * @return the Message
     * @throws IOException
     */
    public Message read() throws IOException {
        return this.parser.deserialize(this.readString());
    }

    /**
     * Writes to string to the socket
     * @param message
     * @throws IOException
     */
    private void send(String message) throws IOException {
        this.writer.write(message);
        this.writer.newLine();
        this.writer.flush();
    }

    /**
     * Reads a line from socket
     * @return
     * @throws IOException
     */
    private String readString() throws IOException {
        return this.reader.readLine();
    }

    /**
     * @return {@link InetAddress} of the socket
     */
    public InetAddress getInetAdress() {
        return this.socket.getInetAddress();
    }

    /**
     * @return The {@link Id} of the Connection
     */
    @Nonnull
    public Id getId() {
        return id;
    }

    public boolean isClosed() {
        return this.socket.isClosed();
    }

    public void close() throws IOException {
        this.socket.close();
    }
}
