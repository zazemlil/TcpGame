package main;

import javax.swing.*;
import java.io.IOException;

public class Main extends JFrame {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SwingUtilities.invokeLater(() -> {
            new MenuFrame("Меню", args);
        });
    }
}
