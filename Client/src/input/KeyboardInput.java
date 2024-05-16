package input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;

import static utilz.Constants.ClientCommands.*;

public class KeyboardInput implements KeyListener {
    PrintWriter outWriter = null;
    JPanel panel;
    public void setStream(PrintWriter outWriter) {
        this.outWriter = outWriter;
    }

    public KeyboardInput(JPanel panel) {
        this.panel = panel;
        panel.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
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
                case KeyEvent.VK_ENTER:
                    JTextField textField = getChatTextField();
                    panel.add(textField);
                    textField.requestFocusInWindow();
                    break;
            }
        }
        catch (Exception ex) {}
    }

    private JTextField getChatTextField() {
        JTextField textField = new JTextField();
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    outWriter.println(CHAT + ":" + textField.getText());
                    outWriter.flush();
                    panel.remove(textField);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        textField.setBounds(300, 50, 200, 30);
        textField.setPreferredSize(new Dimension(200, 30));
        return textField;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
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
        catch (Exception ex) {}
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
