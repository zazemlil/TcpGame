package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static utilz.Constants.PlayerConstants.*;

public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = -123456789L;
    private float x, y;
    private int id;
    private int playerAction = IDLE;
    private int aniIndex = 0;
    private transient BufferedImage currentPlayer = null;

    private void setTexture(BufferedImage[][] animations) {
        currentPlayer = animations[playerAction][aniIndex];
    }

    public BufferedImage getCurrentPlayer() {
        return currentPlayer;
    }
    public void drawPlayer(Graphics g, Texture texture) {
        setTexture(texture.getTexture());
        g.drawImage(currentPlayer, (int)x, (int)y, 316, 250, null);
    }
}
