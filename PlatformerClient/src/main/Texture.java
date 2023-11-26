package main;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import static utilz.Constants.PlayerConstants.*;

public class Texture {
    private BufferedImage[][] animations;
    private BufferedImage[][] mirroredAnimations;
    private BufferedImage imgAttack, imgIdle, imgRun, playerSheet;

    public Texture() {
        importImage();
        loadAnimations();
    }

    public BufferedImage[][] getTexture() {
        return animations;
    }
    public BufferedImage[][] getMirroredTexture() { return mirroredAnimations; }

    private void loadAnimations() {
//        animations = new BufferedImage[3][14];
//
//        for (int j = 0; j < getSpriteAmount(IDLE); j++) {
//            animations[0][j] = imgIdle.getSubimage(j*158, 0, 158, 125);
//        }
//        for (int j = 0; j < getSpriteAmount(RUNNING); j++) {
//            animations[1][j] = imgRun.getSubimage(j*158, 0, 158, 125);
//        }
//        for (int j = 0; j < getSpriteAmount(ATTACK); j++) {
//            animations[2][j] = imgAttack.getSubimage(j*158, 0, 158, 125);
//        }
        createIdleAnimation();
        createRunningAnimation();
        createAttackAnimation();
    }

    private void createIdleAnimation() {
        animations = new BufferedImage[8][16];
        mirroredAnimations = new BufferedImage[8][16];
        for (int j = 0; j < getSpriteAmount(IDLE); j++) {
            // not mirrored
            animations[0][j] = playerSheet.getSubimage(j*140, 0, 140, 93);

            // mirrored
            createMirroredImg(0, j);
        }
    }

    private void createRunningAnimation() {
        for (int j = 0; j < getSpriteAmount(RUNNING); j++) {
            // not mirrored
            animations[1][j] = playerSheet.getSubimage(j*140, 93, 140, 93);

            // mirrored
            createMirroredImg(1, j);
        }
    }

    private void createAttackAnimation() {
        int sprAmount = getSpriteAmount(ATTACK)-2;
        for (int j = 0; j < sprAmount; j++) {
            // not mirrored
            animations[2][j] = playerSheet.getSubimage(j*140, 93*2, 140, 93);

            // mirrored
            createMirroredImg(2, j);
        }
        animations[2][sprAmount] = playerSheet.getSubimage(0, 93*3, 140, 93);
        createMirroredImg(2, sprAmount);
        animations[2][sprAmount+1] = playerSheet.getSubimage(140, 93*3, 140, 93);
        createMirroredImg(2, sprAmount+1);
    }

    private void createMirroredImg(int i, int j) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-animations[i][j].getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        mirroredAnimations[i][j] = op.filter(animations[i][j], null);
    }

    private void importImage() {
        try {
//            imgRun = ImageIO.read(new FileInputStream("res/run.png"));
//            imgIdle = ImageIO.read(new FileInputStream("res/idle.png"));
//            imgAttack = ImageIO.read(new FileInputStream("res/attack.png"));
            playerSheet = ImageIO.read(new FileInputStream("res/Bringer-of-Death-SpritSheet.png"));
            // 140x93  (8x8)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
