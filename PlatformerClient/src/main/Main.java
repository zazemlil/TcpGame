package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int port = 1234;
        if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
            System.out.println(host + " " + port);
        }

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
