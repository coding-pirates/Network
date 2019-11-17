package de.upb.codingpirates.battleships.network;

import de.upb.codingpirates.battleships.network.id.Id;
import de.upb.codingpirates.battleships.network.message.Message;

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

    private Id id;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Parser parser;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Connection(Id id, Socket socket) throws IOException {
        this.id = id;
        this.socket = socket;
        this.outputStream = socket.getOutputStream();
        this.inputStream = socket.getInputStream();
        this.writer = new BufferedWriter(new OutputStreamWriter(this.outputStream, StandardCharsets.UTF_8));
        this.reader = new BufferedReader(new InputStreamReader(this.inputStream, StandardCharsets.UTF_8));
        this.parser = new TestParser();//TODO set to interdoc parser
    }

    /**
     * Parses a {@link Message} to string and sends is through the socket.
     *
     * @param message THe message to parse
     * @throws IOException
     */
    public void send(Message message) throws IOException {
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
    public Id getId() {
        return id;
    }

    public boolean isClosed() {
        return this.socket == null || this.socket.isClosed();
    }

    public void close() throws IOException {
        this.socket.close();
    }
}
