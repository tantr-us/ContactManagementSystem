package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private final String SERVER_ADDRESS;
    private final int SERVER_PORT;

    public ClientSocket(String SERVER_ADDRESS, int SERVER_PORT) {
        this.SERVER_ADDRESS = SERVER_ADDRESS;
        this.SERVER_PORT = SERVER_PORT;
    }

    public void connect() throws IOException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("Connected to the server at " + SERVER_ADDRESS + ":" + SERVER_PORT);
    }

    public void sendObject(Object object) throws IOException {
        outputStream.writeObject(object);
        outputStream.flush();
    }

    public Object receiveObject() throws IOException, ClassNotFoundException {
        return inputStream.readObject();
    }

    public void close() throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
