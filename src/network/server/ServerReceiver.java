package network.server;

import java.io.ObjectInputStream;

public class ServerReceiver implements Runnable{

    private ObjectInputStream in;

    public ServerReceiver(ObjectInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {

    }
}
