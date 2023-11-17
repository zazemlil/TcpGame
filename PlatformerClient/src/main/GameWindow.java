package main;

import javax.swing.*;
import java.net.Socket;

public class GameWindow extends JFrame {
    private Socket socket;
    private GameField gameField;
    public GameWindow(Socket socket, GameField gameField, int width, int height) {
        this.socket = socket;
        this.gameField = gameField;

        JFrame window = new JFrame();
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        window.add(gameField);

        window.setVisible(true);
    }
}
