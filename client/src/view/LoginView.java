package view;

import controller.LoginController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<ComboBoxItem> roleField;
    private JButton loginButton;

    public LoginView () {
        setTitle("Login - Contact Management System");
        setSize(400, 200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        initComponent();
        LoginController controller = new LoginController(this);
        loginButton.addActionListener(controller);
    }

    private void initComponent() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(new Insets(10,10,10, 10)));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
        usernamePanel.setBorder(new EmptyBorder(new Insets(5,10,5, 10)));
        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(150,50));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.setBorder(new EmptyBorder(new Insets(5,10,10, 10)));
        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(150, 50));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.X_AXIS));
        rolePanel.setBorder(new EmptyBorder(new Insets(5,10,10, 10)));
        ComboBoxItem [] roles = {
                new ComboBoxItem("ADMIN", 1),
                new ComboBoxItem("USER", 2),
        };
        roleField = new JComboBox<>(roles);
        roleField.setMaximumSize(new Dimension(150, 50));
        rolePanel.add(new JLabel("Login as: "));
        rolePanel.add(roleField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(new EmptyBorder(new Insets(5,10,10, 10)));
        loginButton = new JButton("LOGIN");
        buttonPanel.add(loginButton);

        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(rolePanel);
        mainPanel.add(buttonPanel);
        this.getContentPane().add(mainPanel, BorderLayout.NORTH);
    }

    public String getUsername() throws Exception {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username or password is incorrect.", "Login Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Username or password is incorrect.");
        }
        return username;
    }

    public String getPassword() throws Exception {
        String password = new String(passwordField.getPassword());
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username or password is incorrect.", "Login Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Username or password is incorrect.");
        }
        return password;
    }

    public int getRole() {
        ComboBoxItem role = (ComboBoxItem) roleField.getSelectedItem();
        assert role != null;
        return role.getValue();
    }
}

class ComboBoxItem {
    private final String key;
    private final int value;

    public ComboBoxItem(String key, int value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }

    public int getValue() {
        return value;
    }
}