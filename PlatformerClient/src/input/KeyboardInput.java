package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;

import static utilz.Constants.ClientCommands.*;

public class KeyboardInput implements KeyListener {
    PrintWriter outWriter = null;
    public void setStream(PrintWriter outWriter) {
        this.outWriter = outWriter;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                outWriter.println(BUTTON_W);
                outWriter.flush();
                break;
            case KeyEvent.VK_A:
                outWriter.println(BUTTON_A);
                outWriter.flush();
                break;
            case KeyEvent.VK_S:
                outWriter.println(BUTTON_S);
                outWriter.flush();
                break;
            case KeyEvent.VK_D:
                outWriter.println(BUTTON_D);
                outWriter.flush();
                break;
            case KeyEvent.VK_SPACE:
                outWriter.println(SPACE);
                outWriter.flush();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
            case KeyEvent.VK_SPACE:
                outWriter.println(DEFAULT);
                break;
        }
    }
}
