package controller;

import share.DTO.UserDTO;
import share.Logging.LogHandler;
import socket.SocketHandler;
import view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;

public class LoginController  implements ActionListener {
    private final LoginView view;
    public LoginController(LoginView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String username = view.getUsername();
            String password = view.getPassword();
            int role = view.getRole();
            UserDTO userDTO = new UserDTO(username, password, role);

            SocketHandler socketHandler = new SocketHandler("127.0.0.1", 4260);
            socketHandler.sendData(userDTO);
            socketHandler.closeConnection();

        } catch (Exception ex) {
            view.showErrorDialog("Error", ex.getMessage());
            LogHandler.log(Level.SEVERE, ex.getMessage());
        }
    }
}
