package main;

import input.KeyboardInput;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GameField extends JPanel {
    private ArrayList<Player> players;
    private int currentPlayerId;
    private Texture texture;
    private KeyboardInput keyboardInput;
    private JLabel jLabel1;
    private JLabel jLabel2;

    private Client client;

    private boolean death_shown = false;

    public GameField(ArrayList<Player> players) {
        this.players = players;
        this.texture = new Texture();

        Dimension labelSize = new Dimension(80, 80);
        Font font = new Font("Verdana", Font.PLAIN, 12);
        jLabel1 = new JLabel("FPS: -");
        jLabel1.setVerticalAlignment(JLabel.TOP);
        jLabel1.setHorizontalAlignment(JLabel.LEFT);
        jLabel1.setPreferredSize(labelSize);
        jLabel1.setFont(font);
        jLabel1.setForeground(Color.GREEN);

        jLabel2 = new JLabel("Ping: -");
        jLabel2.setVerticalAlignment(JLabel.TOP);
        jLabel2.setHorizontalAlignment(JLabel.LEFT);
        jLabel2.setPreferredSize(labelSize);
        jLabel2.setFont(font);
        jLabel2.setForeground(Color.GREEN);
        this.add(jLabel1);
        this.add(jLabel2);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setBackground(Color.darkGray);

        keyboardInput = new KeyboardInput(this);
    }

    public void setFpsPing(int fps, int ping) {
        jLabel1.setText("FPS: " + fps);
        jLabel2.setText("Ping: " + ping);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setCurrentPlayerId() {
        this.currentPlayerId = players.get(players.size() - 1).getId();
    }

    public void setClient(Client c) {
        this.client = c;
    }

    public void setKeyboardStream(PrintWriter outWriter) {
        keyboardInput.setStream(outWriter);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Player item : players) {
            if(item.getId() == currentPlayerId) {
                if(item.isDeath() && !death_shown) {
                    death_shown = true;
                    JButton respawn = new JButton("respawn");
                    respawn.setSize(200, 40);
                    respawn.setAlignmentX(Component.CENTER_ALIGNMENT);
                    respawn.addActionListener(e -> SwingUtilities.invokeLater(() -> {
                        client.inGame = false;
                        death_shown = false;
                        GameField.this.remove(respawn);
                    }));

                    this.add(respawn);
                }
            }

            item.drawPlayer(g, texture);
        }
    }
}
