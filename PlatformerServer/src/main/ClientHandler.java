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
    private static final int SERVER_FPS_SET = 1000;

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
            players.add(my);
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
        double timePerFrame = 1000000000.0 / SERVER_FPS_SET;
        long lastFrame = System.nanoTime();
        long now = 0;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while (my.getActive()) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                my.update();
                lastFrame = now;
                frames++;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println(frames);
                frames = 0;
            }
        }
    }
}
