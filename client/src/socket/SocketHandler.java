package socket;

import share.Logging.LogHandler;

import java.io.IOException;
import java.util.logging.Level;

public class SocketHandler {
    private final ClientSocket clientSocket;

    public SocketHandler(String serverAddress, int serverPort) {
        clientSocket = new ClientSocket(serverAddress, serverPort);
    }

    public void sendData(Object data) {
        try {
            clientSocket.connect();
            clientSocket.sendObject(data);
        } catch (IOException e) {
            LogHandler.log(Level.SEVERE, e.getMessage());
        }
    }

    public void receiveData() {
        try {
            Object response = clientSocket.receiveObject();
            System.out.println("Received data: " + response);
        } catch (ClassNotFoundException | IOException e) {
            LogHandler.log(Level.SEVERE, e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            LogHandler.log(Level.SEVERE, e.getMessage());
        }
    }
}
