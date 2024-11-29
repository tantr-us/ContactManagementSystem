package controller;

import share.UserDTO;
import view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            System.out.println(userDTO.toString());
        } catch (Exception _) {}
    }
}
