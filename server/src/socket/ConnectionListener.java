package socket;

import share.DTO.UserDTO;
import share.Logging.LogHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class ConnectionListener {

    public void run() {
        int THREAD_SIZE = 10;
        int PORT = 4260;

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_SIZE);
        try (ServerSocket server = new ServerSocket(PORT)) {
            //noinspection InfiniteLoopStatement
            while (true) {
                try {
                    LogHandler.log(Level.INFO, "Listening for connection...");
                    Socket connection = server.accept();
                    pool.submit(new ConnectionHandler(connection));
                } catch (IOException _) {}
            }
        } catch (IOException e) {
            System.out.println("Fail to create server");
            LogHandler.log(Level.SEVERE, e.getMessage());
        }
    }
}

class ConnectionHandler implements Runnable {
    private final Socket connection;

    public ConnectionHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            LogHandler.log(Level.INFO, "Received new connection from " + connection.getInetAddress().getCanonicalHostName());

            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            UserDTO userDTO = (UserDTO) in.readObject();
            System.out.println(userDTO.toString());

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (IOException _) {}
        }
    }
}