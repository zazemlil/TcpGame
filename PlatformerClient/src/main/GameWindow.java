package main;

import javax.swing.*;

public class GameWindow extends JFrame {
    GameField gameField;
    public GameWindow(GameField gameField, int width, int height) {
        this.gameField = gameField;

        JFrame window = new JFrame();
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        window.add(gameField);

        window.setVisible(true);
    }
}
