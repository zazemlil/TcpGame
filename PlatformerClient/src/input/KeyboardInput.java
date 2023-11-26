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
            case KeyEvent.VK_UP:
                outWriter.println(BUTTON_W);
                outWriter.flush();
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                outWriter.println(BUTTON_A);
                outWriter.flush();
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                outWriter.println(BUTTON_S);
                outWriter.flush();
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
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
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                outWriter.println(BUTTON_W_RM);
                outWriter.flush();
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                outWriter.println(BUTTON_A_RM);
                outWriter.flush();
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                outWriter.println(BUTTON_S_RM);
                outWriter.flush();
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                outWriter.println(BUTTON_D_RM);
                outWriter.flush();
                break;
            case KeyEvent.VK_SPACE:
                outWriter.println(SPACE_RM);
                outWriter.flush();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
