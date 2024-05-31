package main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientUpdater implements Runnable {
    private Socket socket;
    private ObjectOutputStream outObj;
    private volatile ArrayList<Player> players;
    private final int UPS_SET = 1000;

    public ClientUpdater(Socket socket, ArrayList<Player> players) throws IOException {
        this.players = players;
        this.socket = socket;
        outObj = new ObjectOutputStream(socket.getOutputStream());
        (new Thread(this)).start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();

        int frames = 0;
        double deltaU = 0;

        long lastCheck = System.currentTimeMillis();
        try {
            while (!socket.isClosed()) {
                long currentTime = System.nanoTime();
                deltaU += (currentTime - previousTime) / timePerFrame;
                previousTime = currentTime;

                if (deltaU >= 1) {
                    synchronized (players) {
                        outObj.writeObject(players);
                    }
                    outObj.reset();
                    outObj.flush();
                    frames++;
                    deltaU--;
                }

                if (System.currentTimeMillis() - lastCheck >= 1000) {
                    lastCheck = System.currentTimeMillis();
                    frames = 0;
                }
            }
        } catch (IOException e) {
        }
    }
}
