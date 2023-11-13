package main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientUpdater implements Runnable {
    Thread thread;
    private Socket clientSocket;
    private ArrayList<Player> players;
    public ClientUpdater(Socket clientSocket, ArrayList<Player> players) {
        this.clientSocket = clientSocket;
        this.players = players;
        thread = new Thread();
        thread.start();
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outStr = new ObjectOutputStream(clientSocket.getOutputStream());
            outStr.flush();
            while (true) {
                Thread.sleep(100);
                outStr.writeObject(players);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
