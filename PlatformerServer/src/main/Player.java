package main;

import java.io.*;

import static utilz.Constants.PlayerConstants.*;

public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = -123456789L;
    private float x, y;
    private int id;
    private int playerAction = IDLE;
    private int aniIndex = 0;
    private transient boolean up = false, left = false, down = false, right = false;
    private transient boolean shooting = false;
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

    public void update() {
        updatePosition();
        setAnimation();
        updateAnimationTick();
    }

    private void setAnimation() {
        int startAni = playerAction;

        if (shooting) {
            playerAction = ATTACK;
        } else if (up || down || left || right) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }

        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
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

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
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
        if (up) {
            this.move(0, -0.3f);
        }
        if (left) {
            this.move(-0.3f, 0);
        }
        if (down) {
            this.move(0, 0.3f);
        }
        if (right) {
            this.move(0.3f, 0);
        }
    }
}
