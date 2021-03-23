package game;

import network.server.Server;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public class GameState implements Serializable {

    @Serial
    private static final long serialVersionUID = 1009774500032120388L;
    private static final int WIDTH = 1024, HEIGHT = 640;

    public GameState() {
        Display.setTitle("RacingGame");
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setResizable(true);
            Display.create();

            while (!Display.isCloseRequested()) {
                Display.update();
            }

            Display.destroy();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private void update() {

    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(10004);
        server.startServer();
        new GameState();
    }
}
