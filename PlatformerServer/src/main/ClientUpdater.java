package main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientUpdater implements Runnable {
    private Socket socket;
    private ObjectOutputStream outObj;
    private volatile ArrayList<Player> players;

    public ClientUpdater(Socket socket, ArrayList<Player> players) throws IOException {
        this.players = players;
        this.socket = socket;
        outObj = new ObjectOutputStream(socket.getOutputStream());
        (new Thread(this)).start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / 1000;
        long lastFrame = System.nanoTime();
        long now = 0;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        try {
            while (!socket.isClosed()) {
                now = System.nanoTime();
                if (now - lastFrame >= timePerFrame) {
                    outObj.writeObject(players);
                    outObj.reset();
                    outObj.flush();
                    lastFrame = now;
                    frames++;
                }

                if (System.currentTimeMillis() - lastCheck >= 1000) {
                    lastCheck = System.currentTimeMillis();
                    frames = 0;
                }
            }
        } catch (IOException e) {
            System.out.println("Error Thread ClientUpdater");
        }
    }
}
