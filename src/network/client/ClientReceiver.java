package network.client;

import game.GameState;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientReceiver implements Runnable {

    private ObjectInputStream in;

    public ClientReceiver(ObjectInputStream in) {
        this.in = in;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                GameState game = (GameState) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void close() throws IOException {
        in.close();
    }


}
