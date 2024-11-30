package socket;

public class SocketHandler {
    private final ClientSocket clientSocket;

    public SocketHandler(String serverAddress, int serverPort) {
        clientSocket = new ClientSocket(serverAddress, serverPort);
    }

    public void sendData(Object data) throws Exception {
        clientSocket.connect();
        clientSocket.sendObject(data);
    }

    public void receiveData() throws Exception {
        Object response = clientSocket.receiveObject();
        System.out.println("Received data: " + response);
    }

    public void closeConnection() throws Exception {
        clientSocket.close();
    }
}
