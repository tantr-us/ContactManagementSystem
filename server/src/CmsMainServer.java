import socket.ConnectionListener;

public class CmsMainServer {
    public static void main(String [] args) {
        new ConnectionListener().run();
    }
}
