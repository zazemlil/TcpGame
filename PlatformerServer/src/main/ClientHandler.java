package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ListIterator;

public class ClientHandler implements Runnable {
    private Thread thread;
    private int id;
    private Socket clientSocket;
    private ArrayList<Player> players;

    public ClientHandler(Socket client, ArrayList<Player> player, int id) {
        thread = new Thread(this);
        this.players = player;
        this.id = id;
        this.clientSocket = client;
        thread.start();
    }

    @Override
    public void run() {
        try {
            BufferedReader inReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ObjectOutputStream outStr = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            String request = "";
            Player my = (Player)in.readObject();

            players.add(my);
            ClientUpdater clientUpdater = new ClientUpdater(clientSocket, players); // trying in other Thread
            while ((request = inReader.readLine()) != null) {
                if (request.equals("BUTTON_W_PRESSED")) {
                    my.move(0, -10);
                    my.updatePosition();
                }
                else if (request.equals("BUTTON_A_PRESSED")) {
                    my.move(-10, 0);
                    my.updatePosition();
                }
                else if (request.equals("BUTTON_S_PRESSED")) {
                    my.move(0, 10);
                    my.updatePosition();
                }
                else if (request.equals("BUTTON_D_PRESSED")) {
                    my.move(10, 0);
                    my.updatePosition();
                }
                System.out.println(players.getLast().getX() + " " + players.getLast().getY());
                //outStr.writeObject(players); // only one step!!!
            }
        } catch (IOException e) {
            System.out.println("Error handling client");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
