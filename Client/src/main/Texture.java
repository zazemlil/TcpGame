package main;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

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
        createIdleAnimation();
        createRunningAnimation();
        createAttackAnimation();
        createDeathAnimation();
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

    private void createDeathAnimation() {
        animations[3][0] = playerSheet.getSubimage(140*5, 93*3, 140, 93);
        createMirroredImg(3, 0);
        animations[3][1] = playerSheet.getSubimage(140*6, 93*3, 140, 93);
        createMirroredImg(3, 1);
        animations[3][2] = playerSheet.getSubimage(140*7, 93*3, 140, 93);
        createMirroredImg(3, 2);
        for (int j = 0; j < 7; j++) {
            // not mirrored
            animations[3][j+3] = playerSheet.getSubimage(j*140, 93*4, 140, 93);

            // mirrored
            createMirroredImg(2, j+3);
        }
    }

    private void createMirroredImg(int i, int j) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-animations[i][j].getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        mirroredAnimations[i][j] = op.filter(animations[i][j], null);
    }

    private void importImage() {
        try {
            playerSheet = ImageIO.read(getClass().getResourceAsStream("/Bringer-of-Death-SpritSheet.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
