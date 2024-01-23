package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    private String host = "localhost";
    private String port = "1234";

    public MenuFrame(String title, String[] args) {
        super(title);
        HandleArgs(args);
        setSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPane = new JPanel();
        setContentPane(mainPane);

        JPanel menuPane = new JPanel();
        menuPane.setLayout(new BoxLayout(menuPane, BoxLayout.Y_AXIS));
        JPanel settingsPane = new JPanel();
        settingsPane.setLayout(new BoxLayout(settingsPane, BoxLayout.Y_AXIS));

        JButton startGameButton = new JButton("Начать игру");
        startGameButton.setSize(200, 40);
        startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGameButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            try {
                new Client(host, port).start();
                setVisible(false);
            } catch (Exception ex) {
                setVisible(true);
                System.out.println("Exception occurred in client: " + ex.getMessage() + '\n' + ex.getStackTrace());
            }
        }));

        JButton settingsButton = new JButton("Настройки");
        settingsButton.setSize(200, 40);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            var pane = getContentPane();
            pane.removeAll();
            pane.invalidate();
            getContentPane().add(settingsPane);
            pane.revalidate();
            pane.repaint();
        }));

        menuPane.add(startGameButton);
        menuPane.add(settingsButton);


        JTextField hostField = new JFormattedTextField();
        hostField.setSize(200, 40);
        hostField.setAlignmentX(Component.CENTER_ALIGNMENT);
        hostField.setText(host);
        JTextField portField = new JFormattedTextField();
        portField.setSize(200, 40);
        portField.setAlignmentX(Component.CENTER_ALIGNMENT);
        portField.setText(port);

        JButton backButton = new JButton("Назад");
        backButton.setSize(200, 40);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            var pane = getContentPane();
            pane.removeAll();
            pane.invalidate();
            pane.add(menuPane);
            host = hostField.getText();
            port = portField.getText();
            pane.revalidate();
            pane.repaint();
        }));

        settingsPane.add(backButton);
        settingsPane.add(hostField);
        settingsPane.add(portField);


        mainPane.add(menuPane);
        pack();
        setSize(new Dimension(500, 500));
        setVisible(true);
    }

    private void HandleArgs(String[] args) {
        if(args.length != 2) return;
        host = args[0];
        port = args[1];
    }
}
