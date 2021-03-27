package network.client;

import game.GameState;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientReceiver implements Runnable {

    private ObjectInputStream in;

    public ClientReceiver(ObjectInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        while(true) {
            try {
                GameState game = (GameState) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
