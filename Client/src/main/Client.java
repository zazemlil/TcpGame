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
    private String nick;
    private String host;
    private int port;

    public boolean inGame = false;

    private long oldTime;
    public Client(String nick, String host, String port) {
        this.nick = nick;
        this.host = host;
        this.port = Integer.parseInt(port);
    }

    public void start() throws IOException, ClassNotFoundException {
        new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    game = new Game(800, 600);
                    while (true) {
                        socket = new Socket(host, port);

                        outRequest = new PrintWriter(socket.getOutputStream(), true);

                        game.getGameField().setKeyboardStream(outRequest);
                        game.getGameField().setClient(Client.this);

                        outRequest.println("only for start loop");

                        outRequest.println("NICK:" + nick);

                        inObj = new ObjectInputStream(socket.getInputStream());
                        ArrayList<Player> players = null;
                        updatePlayers(inObj, players);

                        game.setCurrentPlayerId();

                        inGame = true;

                        while (inGame) updatePlayers(inObj, players);

                        socket.close();
                    }
                }
                catch (Exception e){
                    System.out.println("Exception occurred in client: " + e.getMessage() + '\n' + e.getStackTrace());
                }
                return null;
            }
        }.execute();
    }

    private void updatePlayers(ObjectInputStream inObj, ArrayList<Player> players) throws IOException, ClassNotFoundException {
        players = (ArrayList<Player>) inObj.readObject();
        if(players == null) return;

        long currentTime = System.currentTimeMillis();
        game.setPing((int)(currentTime-oldTime));
        oldTime = currentTime;
        game.setPlayers(players);
    }
}
