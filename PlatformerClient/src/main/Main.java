package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 1234);

        PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), true);
        ObjectOutputStream outStr = new ObjectOutputStream(socket.getOutputStream());
        outStr.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        Game game = new Game(800, 600);
        game.getGameField().setKeyboardStream(outWriter);


        Player player = game.getPlayer();
        if (args.length == 2) {
            player.setX(Integer.parseInt(args[0]));
            player.setY(Integer.parseInt(args[1]));
        }
        outStr.writeObject(player);

        ArrayList<Player> players;
        while ((players = (ArrayList<Player>)in.readObject()) != null) {
            game.setEnemies(players);
        }
    }
}
