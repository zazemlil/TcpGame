package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import static utilz.Constants.PlayerConstants.*;

public class Texture {
    private BufferedImage[][] animations;
    private BufferedImage imgAttack, imgIdle, imgRun;

    public Texture() {
        importImage();
        loadAnimations();
    }

    public BufferedImage[][] getTexture() {
        return animations;
    }

    private void loadAnimations() {
        animations = new BufferedImage[3][14];

        for (int j = 0; j < getSpriteAmount(IDLE); j++) {
            animations[0][j] = imgIdle.getSubimage(j*158, 0, 158, 125);
        }
        for (int j = 0; j < getSpriteAmount(RUNNING); j++) {
            animations[1][j] = imgRun.getSubimage(j*158, 0, 158, 125);
        }
        for (int j = 0; j < getSpriteAmount(ATTACK); j++) {
            animations[2][j] = imgAttack.getSubimage(j*158, 0, 158, 125);
        }
    }

    private void importImage() {
        try {
            imgRun = ImageIO.read(new FileInputStream("res/run.png"));
            imgIdle = ImageIO.read(new FileInputStream("res/idle.png"));
            imgAttack = ImageIO.read(new FileInputStream("res/attack.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
