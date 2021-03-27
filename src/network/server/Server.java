package network.server;

import game.GameState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable{

    private int port;
    private ServerSocket serverSocket;
    private boolean running = false;
    private GameState game;

    public Server(int port) throws IOException {
        this.port = port;

        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        running = true;
        System.out.println("Server started running on port " + port);

        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        closeServer();
    }

    public void closeServer() {
        running = false;
        try {
            serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
