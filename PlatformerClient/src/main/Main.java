package main;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client(args);
        client.start();
    }
}
