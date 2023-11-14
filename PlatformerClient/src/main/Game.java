package main;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Game implements Runnable {
    private GameField gameField;
    private final int FPS_SET = 240;
    private PrintWriter outRequest = null;

    public Game(int width, int height) {
        ArrayList<Player> players = new ArrayList<>();
        gameField = new GameField(players);
        new GameWindow(gameField, width, height);

        (new Thread(this)).start();
    }

    public void setAutoRequest(PrintWriter outRequest) {
        this.outRequest = outRequest;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setPlayers(ArrayList<Player> players) {
        gameField.setPlayers(players);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = 0;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while (true) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                outRequest.println("-");
                gameField.repaint();
                lastFrame = now;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }
}
