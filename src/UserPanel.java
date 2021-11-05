import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    private final JButton logoutButton;
    private final JButton deleteAccountButton;
    private final JLabel welcomeMessage;
    private String username = "";

    public UserPanel() {
        setLayout(null);
        setBackground(Color.GRAY);

        logoutButton = new JButton("Logout");
        deleteAccountButton = new JButton("Delete");
        logoutButton.setFocusable(false);
        deleteAccountButton.setFocusable(false);

        logoutButton.setBounds(
                LoginWindow.WIDTH / 2 - 150,
                LoginWindow.HEIGHT / 2 + 50,
                130,
                30
        );
        add(logoutButton);

        deleteAccountButton.setBounds(
                LoginWindow.WIDTH / 2 + 20,
                LoginWindow.HEIGHT / 2 + 50,
                130,
                30
        );
        add(deleteAccountButton);

        welcomeMessage = new JLabel();
        welcomeMessage.setBounds(
                LoginWindow.WIDTH / 2 - 150,
                LoginWindow.HEIGHT / 2 - 60,
                300,
                20
        );
        welcomeMessage.setBackground(Color.GREEN);
        welcomeMessage.setOpaque(true);
        add(welcomeMessage);
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public JLabel getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
