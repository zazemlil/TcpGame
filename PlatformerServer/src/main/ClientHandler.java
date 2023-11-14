package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private int id;
    private Socket clientSocket;
    private volatile ArrayList<Player> players;
    private ObjectOutputStream outObj;
    private ObjectInputStream inObj;

    public ClientHandler(Socket client, ArrayList<Player> player, int id) throws IOException {
        outObj = new ObjectOutputStream(client.getOutputStream());
        inObj = new ObjectInputStream(client.getInputStream());

        this.players = player;
        this.id = id;
        this.clientSocket = client;
        (new Thread(this)).start();
    }

    @Override
    public void run() {
        Player my = null;
        try {
            my = new Player(100, 100, 200, 200);
            players.add(my);
            while (!clientSocket.isClosed()) {
                outObj.writeObject(players);
                outObj.reset();
                new RequestReader(clientSocket, my);
            }
        }
        catch (IOException e) {
            System.out.println("Error handling client");
        }
        finally {
            players.remove(my);
        }
    }
}
