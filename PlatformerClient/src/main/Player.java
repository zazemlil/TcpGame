package main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Player implements Serializable {
    private static final long serialVersionUID = 0;
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

//    public static void Serialize(ArrayList<Player> rectangles, String path)
//    {
//        try(ObjectOutputStream str = new ObjectOutputStream(new FileOutputStream(path)))
//        {
//            str.writeObject(rectangles);
//        }
//        catch (IOException e)
//        {
//            JOptionPane.showMessageDialog (null, "Can't serialize data!", "Error!", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public static ArrayList<Player> Deserialize(String path)
//    {
//        try(ObjectInputStream str = new ObjectInputStream(new FileInputStream(path)))
//        {
//            Object result = str.readObject();
//            if(result instanceof ArrayList) return (ArrayList<Player>)result;
//            else throw new Exception();
//        }
//        catch (Exception e)
//        {
//            JOptionPane.showMessageDialog (null, "Can't deserialize data!", "Error!", JOptionPane.ERROR_MESSAGE);
//        }
//        return new ArrayList<Player>();
//    }
}
