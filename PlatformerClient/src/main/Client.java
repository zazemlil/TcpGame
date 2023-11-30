package main;

import javax.swing.*;
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
    public Client(String host, String port) {
        this.host = host; // "26.143.189.17";//
        this.port = Integer.parseInt(port);
    }

    public void start() throws IOException, ClassNotFoundException {
        new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                socket = new Socket(host, port);

                outRequest = new PrintWriter(socket.getOutputStream(), true);

                game = new Game(800, 600, socket);
                game.getGameField().setKeyboardStream(outRequest);

                outRequest.println("only for start loop");

                updatePlayers();
                return null;
            }
        }.execute();
    }

    private void updatePlayers() throws IOException, ClassNotFoundException {
        inObj = new ObjectInputStream(socket.getInputStream());
        ArrayList<Player> players;
        long now = System.currentTimeMillis();
        try {
            while ((players = (ArrayList<Player>) inObj.readObject()) != null) {
                long currentTime = System.currentTimeMillis();
                game.setPing((int)(currentTime-now));
                now = currentTime;
                game.setPlayers(players);
            }
        } catch (SocketException e) {
            System.out.println("Socket exception");
        }
    }
}
