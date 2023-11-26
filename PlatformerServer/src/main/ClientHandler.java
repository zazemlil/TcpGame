package main;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private int id;
    private final Socket clientSocket;
    private volatile ArrayList<Player> players;
    private Player my;
    private static final int UPS_SET = 1000;

    public ClientHandler(Socket client, ArrayList<Player> player, int id) {
        my = null;
        this.players = player;
        this.id = id;
        this.clientSocket = client;
        (new Thread(this)).start();
    }

    @Override
    public void run() {
        try {
            my = new Player(100, 100);
            my.setId(id);
            my.setPlayers(players);
            players.add(my);
            my.checkSpawnPoint();
            new ClientUpdater(clientSocket, players);
            new RequestReader(clientSocket, my);
            startLoop();
        }
        catch (IOException e) {
            System.out.println("Error handling client");
        }
        finally {
            players.remove(my);
        }
    }

    private void startLoop() {
        double timePerFrame = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();

        int frames = 0;
        double deltaU = 0;

        long lastCheck = System.currentTimeMillis();
        while (my.getActive()) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                my.update();
                frames++;
                deltaU--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println(frames);
                frames = 0;
            }
        }
    }
}
