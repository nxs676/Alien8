package network.client;

import game.GameInput;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientSender implements Runnable {

    private ObjectOutputStream out;
    GameInput input;


    public ClientSender(ObjectOutputStream out) {
        this.out = out;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while(true) {
            // Listen for key presses.
            try {
                send();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void send() throws IOException {
        out.writeObject(input);
        out.flush();

    }

    public void close() throws IOException {
        out.close();
    }
}
