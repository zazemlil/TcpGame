package main;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends JFrame {
    private static ClientHandler[] clients = new ClientHandler[100];
    private static int clientId = 0;
    private static volatile ArrayList<Player> player = new ArrayList<>();
    public Server() throws IOException {
        JFrame window = new JFrame();
        window.setSize(300, 300);
        window.setTitle("Server");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        startServer();
    }

    private void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);

        while(!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();
            clients[clientId] = new ClientHandler(clientSocket, player, clientId);
            System.out.println("ClientId: " + clientId);
            clientId++;
        }
    }
}
