package main;

import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Game implements Runnable {
    private GameField gameField;
    private GameWindow window;
    private final int FPS_SET = 200;
    private int ping = 0;

    public Game(int width, int height) {
        ArrayList<Player> players = new ArrayList<>();
        gameField = new GameField(players);
        window = new GameWindow(gameField, width, height);

        (new Thread(this)).start();
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setPlayers(ArrayList<Player> players) {
        gameField.setPlayers(players);
    }

    public void setCurrentPlayerId() {
        gameField.setCurrentPlayerId();
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

    public void close() { window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING)); }
}
