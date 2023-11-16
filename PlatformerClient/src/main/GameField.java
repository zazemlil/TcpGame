package main;

import input.KeyboardInput;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GameField extends JPanel {
    private ArrayList<Player> players;
    private Texture texture;
    private KeyboardInput keyboardInput;
    private JLabel jLabel;
    public GameField(ArrayList<Player> players) {
        this.players = players;
        this.texture = new Texture();

        Dimension labelSize = new Dimension(80, 80);
        Font font = new Font("Verdana", Font.PLAIN, 12);
        jLabel = new JLabel("-");
        jLabel.setVerticalAlignment(JLabel.TOP);
        jLabel.setHorizontalAlignment(JLabel.LEFT);
        jLabel.setPreferredSize(labelSize);
        jLabel.setFont(font);
        jLabel.setForeground(Color.GREEN);
        this.add(jLabel);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setBackground(Color.darkGray);

        keyboardInput = new KeyboardInput();
        this.addKeyListener(keyboardInput);
    }

    public void setFps(int fps) {
        jLabel.setText("FPS: " + Integer.toString(fps));
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setKeyboardStream(PrintWriter outWriter) {
        keyboardInput.setStream(outWriter);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Player item : players) {
            item.drawPlayer(g, texture);
        }
    }
}
