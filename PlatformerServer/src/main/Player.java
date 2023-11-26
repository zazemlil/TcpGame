package main;

import java.io.*;
import java.util.ArrayList;

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
    private transient volatile ArrayList<Player> players;
    private transient boolean up = false, left = false, down = false, right = false;
    private transient boolean shooting = false;
    private transient int aniTick = 0, aniSpeed = 80;
    private transient boolean isActive = true;
    private transient int width = 50, height = 140;
    private transient int attackRange = 140, canShoot = 1, damage = 33;
    private transient boolean isDeath = false, stop = false;

    public Player(int x, int y) {
        id = -1;
        this.x = x;
        this.y = y;
    }

    public void checkSpawnPoint() {
        boolean canSpawnInThisPoint = false;
        while (!canSpawnInThisPoint) {
            int f = 1;
            for (Player player : players) {
                if (player.getId() != this.getId())
                {
                    if ((x+width) >= player.getX() && (y+height) >= player.getY()
                            && (x) <= player.getX()+player.getWidth() && (y) <= player.getY()+player.getHeight()) {
                        x += 2;
                        f = 0;
                    };
                }
            }
            if (f == 1) canSpawnInThisPoint = true;
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setActive(boolean b) {
        isActive = b;
    }

    public boolean getActive() {
        return isActive;
    }

    public void update() {
        if (!stop) {
            updatePosition();
            setAnimation();
            updateAnimationTick();
            checkDeath();
        }
    }

    private void setAnimation() {
        int startAni = playerAction;

        if (isDeath) {
            playerAction = DEATH;
        } else if (shooting) {
            if (canShoot == 1 && aniIndex == getSpriteAmount(ATTACK)/2+2) {
                attack();
                canShoot = 0;
            }
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

    private void checkDeath() {
        if (health <= 0) {
            isDeath = true;
        }
    }

    public boolean getIsDeath() {
        return isDeath;
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
                if (playerAction == DEATH) {
                    stop = true;
                    players.remove(this);
                }
                aniIndex = 0;
                if (playerAction == ATTACK) {
                    this.setShooting(false);
                    canShoot = 1;
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
    public void setDamage(int damage) {
        health -= damage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void move(float deltaX, float deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    private void updatePosition() {
        if (up) {
            if (checkCollision(0, -0.3f)) this.move(0, -0.3f);
        }
        if (left) {
            if (checkCollision(-0.3f, 0)) this.move(-0.3f, 0);
        }
        if (down) {
            if (checkCollision(0, 0.3f)) this.move(0, 0.3f);
        }
        if (right) {
            if (checkCollision(0.3f, 0)) this.move(0.3f, 0);
        }
    }

    private boolean checkCollision(float deltaX, float deltaY) {
        for (Player player : players) {
            if (player.getId() != this.getId()) {
                if ((x+width+deltaX) >= player.getX() && (y+height+deltaY) >= player.getY()
                && (x+deltaX) <= player.getX()+player.getWidth() && (y+deltaY) <= player.getY()+player.getHeight()) return false;
            }
        }
        return true;
    }

    private void attack() {
        for (Player player : players) {
            if (player.getId() != this.getId()) {
                if (direction == RIGHT) {
                    if ((x+width+attackRange) >= player.getX()
                            && (y+height) >= player.getY()
                            && y <= player.getY()+player.getHeight()
                            && x+width <= player.getX()) player.setDamage(damage);
                }
                if (direction == LEFT) {
                    if ((x-attackRange) <= player.getX()+player.getWidth()
                            && (y+height) >= player.getY()
                            && y <= player.getY()+player.getHeight()
                            && x >= player.getX()+player.getWidth()) player.setDamage(damage);
                }
            }
        }
    }
}
