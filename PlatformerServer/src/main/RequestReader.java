package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestReader {
    private BufferedReader inReader;
    private final Player clientPlayer;
    private final Socket socket;
    public RequestReader(Socket socket, Player clientPlayer) throws IOException {
        this.socket = socket;
        this.clientPlayer = clientPlayer;
        setInReader();
        startReading();
    }

    private void setInReader() throws IOException {
        inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void startReading() throws IOException {
        String request;
        request = inReader.readLine();
        switch (request) {
            case "BUTTON_W_PRESSED" -> {
                clientPlayer.move(0, -10);
                clientPlayer.updatePosition();
                clientPlayer.setMoving(true);
            }
            case "BUTTON_A_PRESSED" -> {
                clientPlayer.move(-10, 0);
                clientPlayer.updatePosition();
                clientPlayer.setMoving(true);
            }
            case "BUTTON_S_PRESSED" -> {
                clientPlayer.move(0, 10);
                clientPlayer.updatePosition();
                clientPlayer.setMoving(true);
            }
            case "BUTTON_D_PRESSED" -> {
                clientPlayer.move(10, 0);
                clientPlayer.updatePosition();
                clientPlayer.setMoving(true);
            }
            case "PLAYER_IDLE" -> {
                clientPlayer.setMoving(false);
            }
        }
    }
}
