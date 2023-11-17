package main;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;


public class Client {
    private Socket socket;
    private PrintWriter outRequest;
    private ObjectInputStream inObj;
    private Game game;
    private String host;
    private int port;
    public Client(String[] args) {
        host = "localhost";
        port = 1234;
        if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
            System.out.println(host + " " + port);
        }
    }

    public void start() throws IOException, ClassNotFoundException {
        socket = new Socket(host, port);

        outRequest = new PrintWriter(socket.getOutputStream(), true);

        game = new Game(800, 600, socket);
        game.setAutoRequest(outRequest);
        game.getGameField().setKeyboardStream(outRequest);

        updatePlayers();
    }

    private void updatePlayers() throws IOException, ClassNotFoundException {
        inObj = new ObjectInputStream(socket.getInputStream());
        ArrayList<Player> players;
        try {
            while ((players = (ArrayList<Player>) inObj.readObject()) != null) {
                game.setPlayers(players);
            }
        } catch (SocketException e) {
            System.out.println("Socket exception");
        }
    }
}
