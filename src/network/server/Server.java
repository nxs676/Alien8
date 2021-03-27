package network.server;

import game.GameState;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable{

    private int port;
    private ServerSocket serverSocket;
    private boolean running = false;
    private GameState game;
    private int id = 0;

    public Server(int port) throws IOException {
        this.port = port;
        new Thread(this).start();

        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;
        System.out.println("Server started running on port " + port);

        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, id);
                id++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        close();
    }

    public void close() {
        running = false;
        try {
            serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
