package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants.*;

public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = -123456789L;
    private float x, y;
    private int id;
    private int playerAction = IDLE;
    private int aniIndex = 0;
    private int direction = 0;
    private int health = 100;
    private String chatMessage = null;
    private transient BufferedImage currentPlayer = null;

    private void setTexture(BufferedImage[][] animations) {
        currentPlayer = animations[playerAction][aniIndex];
    }

    public BufferedImage getCurrentPlayer() {
        return currentPlayer;
    }
    public void drawPlayer(Graphics g, Texture texture) {
        if (direction == 0 || direction == LEFT) {
            setTexture(texture.getTexture());
            g.drawImage(currentPlayer, (int)x-80, (int)y, 316, 250, null);
        } else if (direction == RIGHT) {
            setTexture(texture.getMirroredTexture());
            g.drawImage(currentPlayer, (int)x+80, (int)y, 316, 250, null);
        }
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Player"+ id + " Health: " + health, (int)x+110, (int)y+270);
        if (chatMessage != null) {
            g.setColor(Color.WHITE);
            Font font = new Font("Arial", Font.BOLD, 18);
            FontMetrics metrics = g.getFontMetrics(font);
            g.setFont(font);
            g.drawString(chatMessage, (int)x+160-metrics.stringWidth(chatMessage)/2, (int)y+70);
        }
    }
}
