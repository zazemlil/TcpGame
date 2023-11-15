package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
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
        Socket socket = new Socket(host, port);

        PrintWriter outRequest = new PrintWriter(socket.getOutputStream(), true);
        ObjectOutputStream outObj = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());

        Game game = new Game(800, 600);
        game.setAutoRequest(outRequest);
        game.getGameField().setKeyboardStream(outRequest);

        ArrayList<Player> players;
        while ((players = (ArrayList<Player>)inObj.readObject()) != null) {
            game.setPlayers(players);
        }
    }
}
