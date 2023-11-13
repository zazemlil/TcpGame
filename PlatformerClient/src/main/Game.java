package main;

import java.util.ArrayList;

public class Game implements Runnable {
    private Thread thread;
    private GameField gameField;
    private final int FPS_SET = 240;
    private Player player;

    public Game(int width, int height) {
        ArrayList<Player> enemy = new ArrayList<>();
//        enemy.add(new Player(100, 200, 30, 30));
//        enemy.add(new Player(200, 300, 30, 30));
//        enemy.add(new Player(500, 400, 100, 150));
        player = new Player(100, 100, 50, 50);
        gameField = new GameField(player, enemy);
        new GameWindow(gameField, width, height);

        startGameLoop();
    }

    private void startGameLoop() {
        thread = new Thread(this);
        thread.start();
    }

    public Player getPlayer() {
        return player;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setEnemies(ArrayList<Player> enemy) {
        gameField.setEnemy(enemy);
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
