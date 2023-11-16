package main;

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
    private transient int playerDir = -1;
    private transient boolean moving = false, shooting = false;
    private transient int aniTick = 0, aniSpeed = 110;
    private transient boolean isActive = true;

    public Player(int x, int y) {
        id = -1;
        this.x = x;
        this.y = y;
    }

    public void setActive(boolean b) {
        isActive = b;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setPlayerDir(int dir) {
        this.playerDir = dir;
    }

    public void update() {
        updatePosition();
        setAnimation();
        updateAnimationTick();
    }

    private void setAnimation() {
        if (shooting) {
            playerAction = ATTACK;
        } else if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
                if (playerAction == ATTACK) {
                    this.setShooting(false);
                }
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void move(float deltaX, float deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    private void updatePosition() {
        if (moving) {
            switch (playerDir) {
                case UP:
                    this.move(0, -0.3f);
                    break;
                case LEFT:
                    this.move(-0.3f, 0);
                    break;
                case DOWN:
                    this.move(0, 0.3f);
                    break;
                case RIGHT:
                    this.move(0.3f, 0);
                    break;
            }
        }
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
