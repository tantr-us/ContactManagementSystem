import org.mindrot.jbcrypt.BCrypt;
import view.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginView();
            }
        });
    }
}
