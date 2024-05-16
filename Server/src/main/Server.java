package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ClientHandler[] clients = new ClientHandler[100];
    private static int nextClientId = 0;
    private static volatile ArrayList<Player> player = new ArrayList<>();
    public Server() throws IOException {
        startServer();
    }

    private void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);

        while(!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();
            clients[nextClientId] = new ClientHandler(clientSocket, player, nextClientId);
            System.out.println("Registered new client, id: #" + nextClientId);
            nextClientId++;
        }
    }
}
