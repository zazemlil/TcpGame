package main;

import javax.swing.*;
import java.net.Socket;

public class GameWindow extends JFrame {
    private GameField gameField;
    public GameWindow(GameField gameField, int width, int height) {
        super();
        this.setSize(width, height);
        this.gameField = gameField;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.add(gameField);

        this.setVisible(true);
    }
}
