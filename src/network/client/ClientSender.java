package network.client;

import java.io.ObjectOutputStream;

public class ClientSender implements Runnable {

    private ObjectOutputStream out;

    public ClientSender(ObjectOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {

    }
}
