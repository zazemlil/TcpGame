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

    MenuFrame parentWindow;

    public boolean inGame = false;
    private boolean showGameWindow = true;

    private long oldTime;
    public Client(String nick, String host, String port, MenuFrame parent) {
        this.nick = nick;
        this.host = host;
        this.port = Integer.parseInt(port);
        this.parentWindow = parent;
    }

    public void start() throws IOException, ClassNotFoundException {
        new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    game = new Game(800, 600);
                    game.getGameField().setClient(Client.this);

                    while (showGameWindow) {
                        socket = new Socket(host, port);

                        outRequest = new PrintWriter(socket.getOutputStream(), true);

                        game.getGameField().setKeyboardStream(outRequest);

                        outRequest.println("only for start loop");

                        outRequest.println("NICK:" + nick);

                        inObj = new ObjectInputStream(socket.getInputStream());
                        updatePlayers(inObj);

                        game.setCurrentPlayerId();

                        inGame = true;

                        while (inGame) updatePlayers(inObj);

                        socket.close();
                    }
                    parentWindow.setVisible(true);
                    game.close();
                }
                catch (Exception e){
                    System.out.println("Exception occurred in client: " + e.getMessage() + '\n' + e.getStackTrace());
                }
                return null;
            }
        }.execute();
    }

    private void updatePlayers(ObjectInputStream inObj) throws IOException, ClassNotFoundException {
        ArrayList<Player> players = (ArrayList<Player>) inObj.readObject();
        if(players == null) return;

        long currentTime = System.currentTimeMillis();
        game.setPing((int)(currentTime-oldTime));
        oldTime = currentTime;
        game.setPlayers(players);
    }

    public void exit() {
        this.showGameWindow = false;
        this.inGame = false;
    }
}
