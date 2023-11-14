package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    private static ClientHandler[] clients = new ClientHandler[100];
    private static int clientId = 0;
    private static volatile ArrayList<Player> player = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);

        while(!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();
            clients[clientId] = new ClientHandler(clientSocket, player, clientId);
            System.out.println("ClientId: " + clientId);
            clientId++;
        }
    }
}
