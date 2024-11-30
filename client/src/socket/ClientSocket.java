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
        System.out.println("Connected to the server at " + SERVER_ADDRESS + ":" + SERVER_PORT);
    }

    public void sendObject(Object object) throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(object);
        outputStream.flush();
    }

    public Object receiveObject() throws IOException, ClassNotFoundException {
        inputStream = new ObjectInputStream(socket.getInputStream());
        return inputStream.readObject();
    }

    public void close() throws IOException {
        assert outputStream != null;
        outputStream.close();
        assert inputStream != null;
        inputStream.close();
        socket.close();
    }
}
