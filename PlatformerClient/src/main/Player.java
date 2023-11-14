package main;

import java.awt.*;
import java.io.*;

public class Player implements Serializable {
    private static final long serialVersionUID = -123456789L;
    private int x, y, id;
    private int sizeX, sizeY;
    private Rectangle rectangle;

    public Player(int x, int y, int sizeX, int sizeY) {
        id = -1;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        rectangle = new Rectangle(x, y, sizeX, sizeY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Rectangle getTexture() {
        return rectangle;
    }

    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public void updatePosition() {
        rectangle.setLocation(x, y);
    }
}
