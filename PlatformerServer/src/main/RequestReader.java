package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static utilz.Constants.Directions.*;


public class RequestReader implements Runnable {
    private BufferedReader inReader;
    private Player clientPlayer;
    private final Socket socket;
    public RequestReader(Socket socket, Player clientPlayer) throws IOException {
        this.socket = socket;
        this.clientPlayer = clientPlayer;
        setInReader();
        startReading();
        (new Thread(this)).start();
    }

    private void setInReader() throws IOException {
        inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void startReading() throws IOException, NullPointerException {
        String request = "";
        request = inReader.readLine();

        if (!clientPlayer.getIsDeath()) {
            switch (request) {
                case "BUTTON_W_PRESSED" -> {
                    clientPlayer.setUp(true);
                    break;
                }
                case "BUTTON_A_PRESSED" -> {
                    clientPlayer.setLeft(true);
                    clientPlayer.setDirection(LEFT);
                    break;
                }
                case "BUTTON_S_PRESSED" -> {
                    clientPlayer.setDown(true);
                    break;
                }
                case "BUTTON_D_PRESSED" -> {
                    clientPlayer.setRight(true);
                    clientPlayer.setDirection(RIGHT);
                    break;
                }
                case "BUTTON_SPACE_PRESSED" -> {
                    clientPlayer.setShooting(true);
                    break;
                }
                case "BUTTON_W_REMOVED" -> {
                    clientPlayer.setUp(false);
                    break;
                }
                case "BUTTON_A_REMOVED" -> {
                    clientPlayer.setLeft(false);
                    break;
                }
                case "BUTTON_S_REMOVED" -> {
                    clientPlayer.setDown(false);
                    break;
                }
                case "BUTTON_D_REMOVED" -> {
                    clientPlayer.setRight(false);
                    break;
                }
                case "BUTTON_SPACE_REMOVED" -> {
                    break;
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                startReading();
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Error Thread RequestReader");
            clientPlayer.setActive(false);
        }
    }
}
