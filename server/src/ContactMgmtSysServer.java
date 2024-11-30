import socket.ConnectionListener;

public class ContactMgmtSysServer {
    public static void main(String [] args) {
        new ConnectionListener().run();
    }
}
