package network.server;

import network.client.Client;

import java.net.Socket;

public class ClientHandler implements Runnable {

    Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
