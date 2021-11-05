import javax.swing.*;
import java.awt.*;

public class SignUpPanel extends JPanel {
    private final JButton signUpButton;
    private final JButton signInButton;
    private final JTextField firstNameInput;
    private final JTextField lastNameInput;
    private final JTextField usernameInput;
    private final JPasswordField passwordInput;
    private final JLabel errorMessage;

    public SignUpPanel() {
        setLayout(null);
        setBackground(Color.GRAY);

        signUpButton = new JButton("Sign up");
        signInButton = new JButton("Sign in");
        signUpButton.setFocusable(false);
        signInButton.setFocusable(false);
        usernameInput = new JTextField("");
        passwordInput = new JPasswordField("");
        firstNameInput = new JTextField("");
        lastNameInput = new JTextField("");

        signInButton.setBounds(
                LoginWindow.WIDTH / 2 + 20,
                LoginWindow.HEIGHT / 2 + 50,
                130,
                30
        );
        add(signInButton);

        signUpButton.setBounds(
                LoginWindow.WIDTH / 2 - 150,
                LoginWindow.HEIGHT / 2 + 50,
                130,
                30
        );
        add(signUpButton);

        JLabel firstName = new JLabel("First name");
        firstName.setBounds(
                LoginWindow.WIDTH / 2 - 150,
                LoginWindow.HEIGHT / 2 - 150,
                100,
                20
        );
        add(firstName);
        firstNameInput.setBounds(
                LoginWindow.WIDTH / 2 - 50,
                LoginWindow.HEIGHT / 2 - 150,
                200,
                20
        );
        add(firstNameInput);

        JLabel lastName = new JLabel("Last name");
        lastName.setBounds(
                LoginWindow.WIDTH / 2 - 150,
                LoginWindow.HEIGHT / 2 - 110,
                100,
                20
        );
        add(lastName);
        lastNameInput.setBounds(
                LoginWindow.WIDTH / 2 - 50,
                LoginWindow.HEIGHT / 2 - 110,
                200,
                20
        );
        add(lastNameInput);

        JLabel username = new JLabel("Username");
        username.setBounds(
                LoginWindow.WIDTH / 2 - 150,
                LoginWindow.HEIGHT / 2 - 70,
                100,
                20
        );
        add(username);
        usernameInput.setBounds(
                LoginWindow.WIDTH / 2 - 50,
                LoginWindow.HEIGHT / 2 - 70,
                200,
                20
        );
        add(usernameInput);

        JLabel password = new JLabel("Password");
        password.setBounds(
                LoginWindow.WIDTH / 2 - 150,
                LoginWindow.HEIGHT / 2 - 30,
                100,
                20
        );
        add(password);
        passwordInput.setBounds(
                LoginWindow.WIDTH / 2 - 50,
                LoginWindow.HEIGHT / 2 - 30,
                200,
                20
        );
        add(passwordInput);

        errorMessage = new JLabel();
        errorMessage.setBounds(
                LoginWindow.WIDTH / 2 - 150,
                LoginWindow.HEIGHT / 2 + 5,
                300,
                20
        );
        errorMessage.setBackground(Color.RED);
        add(errorMessage);
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public JButton getSignInButton() {
        return signInButton;
    }

    public JTextField getFirstNameInput() {
        return firstNameInput;
    }

    public JTextField getLastNameInput() {
        return lastNameInput;
    }

    public JTextField getUsernameInput() {
        return usernameInput;
    }

    public JPasswordField getPasswordInput() {
        return passwordInput;
    }

    public JLabel getErrorMessage() {
        return errorMessage;
    }

    public String getPasswordAsString() {
        StringBuilder passwordAsString = new StringBuilder();
        for (char symbol: passwordInput.getPassword()) {
            passwordAsString.append(symbol);
        }
        return passwordAsString.toString();
    }
}
