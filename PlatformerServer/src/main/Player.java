package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.Transient;
import java.io.*;

import static utilz.Constants.PlayerConstants.*;

public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = -123456789L;
    private int x, y, id;
    private int sizeX, sizeY;
    private Rectangle rectangle;
    private int playerAction = IDLE;
    private int aniIndex = 0;
    private transient int playerDir = -1;
    private transient boolean moving = false;
    private transient int aniTick = 0, aniSpeed = 135;


    public Player(int x, int y, int sizeX, int sizeY) {
        id = -1;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        rectangle = new Rectangle(x, y, sizeX, sizeY);
    }

    @Transient
    public void update() {
        setAnimation();
        updateAnimationTick();
    }

    @Transient
    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    @Transient
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    public Rectangle getTexture() {
        return rectangle;
    }

    @Transient
    public int getX() {
        return x;
    }

    @Transient
    public int getY() {
        return y;
    }

    @Transient
    public void setX(int x) {
        this.x = x;
    }

    @Transient
    public void setY(int y) {
        this.y = y;
    }

    @Transient
    public void setId(int id) {
        this.id = id;
    }

    @Transient
    public int getId() {
        return id;
    }

    @Transient
    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    @Transient
    public void updatePosition() {
        rectangle.setLocation(x, y);
    }

    @Transient
    public void setDirection(int direction) {
        this.playerDir = direction;
    }

    @Transient
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
