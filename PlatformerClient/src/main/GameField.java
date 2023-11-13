package main;

import input.KeyboardInput;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;

public class GameField extends JPanel {
    private Player player;
    private ArrayList<Player> enemy;
    private KeyboardInput keyboardInput;
    public GameField(Player player, ArrayList<Player> enemy) {
        this.player = player;
        this.enemy = enemy;
        this.enemy.add(player);

        this.setFocusable(true);
        this.requestFocusInWindow();

        keyboardInput = new KeyboardInput(player);
        this.addKeyListener(keyboardInput);
    }

    public void setEnemy(ArrayList<Player> enemy) {
        this.enemy = enemy;
    }

    public void setKeyboardStream(PrintWriter outWriter) {
        keyboardInput.setStream(outWriter);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        player.updatePosition();
        //g2d.draw(player.getTexture());

        for (Player item : enemy) {
            System.out.println(item.getX() + " " + item.getY());
            g2d.draw(item.getTexture());
        }
    }
}
