package network.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Client implements Runnable {

    private String host;
    private int port;
    private Socket socket;
    private ClientSender sender;
    private ClientReceiver receiver;
    private int id;


    private boolean running;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        Client client1 = new Client("localhost", 10004);
        client1.connect();

        Client client2 = new Client("localhost", 10004);
        client2.connect();


    }

    public void connect() {
        try {
            socket = new Socket(host, port);
            receiver = new ClientReceiver(new ObjectInputStream(socket.getInputStream()));
            sender = new ClientSender(new ObjectOutputStream(socket.getOutputStream()));
            new Thread(this).start();
            startThreads();
        } catch (ConnectException e) {
            System.out.println("Unable to connect to server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startThreads() throws IOException {
        new Thread(sender).start();
        new Thread(receiver).start();
    }

    @Override
    public void run() {
        running = true;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        close();
    }

    public void close() {
        try {
            running = false;
            socket.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}