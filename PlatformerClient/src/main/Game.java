package main;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Game implements Runnable {
    private Socket socket;
    private GameField gameField;
    private final int FPS_SET = 500;
    private int ping = 0;

    public Game(int width, int height, Socket socket) {
        this.socket = socket;
        ArrayList<Player> players = new ArrayList<>();
        gameField = new GameField(players);
        new GameWindow(socket, gameField, width, height);

        (new Thread(this)).start();
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setPlayers(ArrayList<Player> players) {
        gameField.setPlayers(players);
    }
    public void setPing(int ping) {
        this.ping = ping;
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long previousTime = System.nanoTime();

        int frames = 0;
        double deltaF = 0;

        long lastCheck = System.currentTimeMillis();
        while (true) {
            long currentTime = System.nanoTime();
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaF >= 1) {
                gameField.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " Ping: " + ping);
                gameField.setFpsPing(frames, ping);
                frames = 0;
            }
        }
    }
}
