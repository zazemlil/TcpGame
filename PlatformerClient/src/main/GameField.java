package main;

import input.KeyboardInput;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GameField extends JPanel {
    private ArrayList<Player> players;
    private KeyboardInput keyboardInput;
    public GameField(ArrayList<Player> players) {
        this.players = players;

        this.setFocusable(true);
        this.requestFocusInWindow();

        keyboardInput = new KeyboardInput();
        this.addKeyListener(keyboardInput);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setKeyboardStream(PrintWriter outWriter) {
        keyboardInput.setStream(outWriter);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        for (Player item : players) {
            g2d.draw(item.getTexture());
        }
    }
}
