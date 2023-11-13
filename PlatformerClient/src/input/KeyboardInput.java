package input;

import main.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;

public class KeyboardInput implements KeyListener {
    private Player player;
    PrintWriter outWriter = null;
    public KeyboardInput(Player player) {
        this.player = player;
    }

    public void setStream(PrintWriter outWriter) {
        this.outWriter = outWriter;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                outWriter.println("BUTTON_W_PRESSED");
                //player.move(0, -5);
                break;
            case KeyEvent.VK_A:
                outWriter.println("BUTTON_A_PRESSED");
                //player.move(-5, 0);
                break;
            case KeyEvent.VK_S:
                outWriter.println("BUTTON_S_PRESSED");
                //player.move(0, 5);
                break;
            case KeyEvent.VK_D:
                outWriter.println("BUTTON_D_PRESSED");
                //player.move(5, 0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                break;
        }
    }
}
