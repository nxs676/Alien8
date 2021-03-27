package network.server;

import game.Message;
import game.GameState;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    GameState game;
    Message input;
    private int id;

    public ClientHandler(Socket socket, int id) throws IOException {
        this.socket = socket;
        new Thread(this).start();

        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(socket.isConnected()) {
            try {
                input = (Message) in.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(GameState game) {
        try {
            out.writeObject(game);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
