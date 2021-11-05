import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class LoginWindow extends JFrame implements ActionListener {
    private final SignInPanel signInPanel;
    private final SignUpPanel signUpPanel;
    private final UserPanel userPanel;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private final Statement statement;

    public static void main(String[] args) throws SQLException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        new LoginWindow();
    }

    public LoginWindow() throws SQLException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        setTitle("Login");
        setBounds(300, 200, LoginWindow.WIDTH, LoginWindow.HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        signInPanel = new SignInPanel();
        signUpPanel = new SignUpPanel();
        userPanel = new UserPanel();

        signInPanel.getSignInButton().addActionListener(this);
        signInPanel.getSignUpButton().addActionListener(this);

        signUpPanel.getSignInButton().addActionListener(this);
        signUpPanel.getSignUpButton().addActionListener(this);

        userPanel.getLogoutButton().addActionListener(this);
        userPanel.getDeleteAccountButton().addActionListener(this);

        add(signInPanel);
        setVisible(true);

        String database_url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "QWer12!!";
        String driver_url = "com.mysql.cj.jdbc.Driver";

        Class.forName(driver_url).getDeclaredConstructor().newInstance();
        Connection connection = DriverManager.getConnection(database_url, user, password);
        statement = connection.createStatement();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInPanel.getSignUpButton()) {
            cleanSignInPanel();
            changePanel(signInPanel, signUpPanel);
            setTitle("Registration");
        }

        if (e.getSource() == signInPanel.getSignInButton()) {
            if (signInPanel.getUsernameInput().getText().trim().equals("")) {
                signInPanel.getErrorMessage().setText("Enter username");
                signInPanel.getErrorMessage().setOpaque(true);
            } else if (signInPanel.getPasswordAsString().equals("")) {
                signInPanel.getErrorMessage().setText("Enter password");
                signInPanel.getErrorMessage().setOpaque(true);
            } else {
                try {
                    ResultSet dataSet = statement.executeQuery(
                            "select * from users where username='" +
                                    signInPanel.getUsernameInput().getText().trim() + "';"
                    );

                    if (dataSet.next()) {
                        String truePassword = dataSet.getString("password");
                        if (signInPanel.getPasswordAsString().equals(truePassword)) {
                            changePanel(signInPanel, userPanel);
                            cleanSignInPanel();
                            setTitle("Welcome");

                            userPanel.getWelcomeMessage().setText(
                                    "Welcome, " + dataSet.getString("first_name") +
                                            " " + dataSet.getString("last_name") + "!"
                            );

                            userPanel.setUsername(dataSet.getString("username"));
                        } else {
                            signInPanel.getErrorMessage().setText("Incorrect password");
                            signInPanel.getErrorMessage().setOpaque(true);
                        }
                    } else {
                        signInPanel.getErrorMessage().setText("Username not found");
                        signInPanel.getErrorMessage().setOpaque(true);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == signUpPanel.getSignInButton()) {
            cleanSignUpPanel();
            changePanel(signUpPanel, signInPanel);
            setTitle("Login");
        }

        if (e.getSource() == signUpPanel.getSignUpButton()) {
            if (signUpPanel.getFirstNameInput().getText().trim().equals("")) {
                signUpPanel.getErrorMessage().setText("Enter first name");
                signUpPanel.getErrorMessage().setOpaque(true);
            } else if (signUpPanel.getLastNameInput().getText().trim().equals("")) {
                signUpPanel.getErrorMessage().setText("Enter last name");
                signUpPanel.getErrorMessage().setOpaque(true);
            } else if (signUpPanel.getUsernameInput().getText().trim().equals("")) {
                signUpPanel.getErrorMessage().setText("Enter username");
                signUpPanel.getErrorMessage().setOpaque(true);
            } else if (signUpPanel.getPasswordAsString().equals("")) {
                signUpPanel.getErrorMessage().setText("Enter password");
                signUpPanel.getErrorMessage().setOpaque(true);
            } else {
                try {
                    ResultSet dataSet = statement.executeQuery(
                            "select * from users where username='" +
                                    signUpPanel.getUsernameInput().getText().trim() + "';"
                    );

                    if (!dataSet.next()) {
                        statement.executeUpdate(
                                "insert users(first_name, last_name, username, password) " +
                                        "values ('" +
                                        signUpPanel.getFirstNameInput().getText().trim() + "', '" +
                                        signUpPanel.getLastNameInput().getText().trim() + "', '" +
                                        signUpPanel.getUsernameInput().getText().trim() + "', '" +
                                        signUpPanel.getPasswordAsString() + "');"
                        );

                        cleanSignUpPanel();
                        changePanel(signUpPanel, signInPanel);
                        setTitle("Login");
                    } else {
                        signUpPanel.getErrorMessage().setText("Username already exists");
                        signUpPanel.getErrorMessage().setOpaque(true);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == userPanel.getLogoutButton()) {
            userPanel.setUsername("");
            changePanel(userPanel, signInPanel);
            setTitle("Login");
        }

        if (e.getSource() == userPanel.getDeleteAccountButton()) {
            try {
                statement.executeUpdate(
                        "delete from users where username='" +
                                userPanel.getUsername() + "';"
                );
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            userPanel.setUsername("");
            changePanel(userPanel, signInPanel);
            setTitle("Login");
        }
    }

    private void changePanel (JPanel lastPanel, JPanel nextPanel) {
        remove(lastPanel);
        repaint();
        revalidate();

        add(nextPanel);
        repaint();
        revalidate();
    }

    private void cleanSignInPanel() {
        signInPanel.getUsernameInput().setText("");
        signInPanel.getPasswordInput().setText("");
        signInPanel.getErrorMessage().setText("");
        signInPanel.getErrorMessage().setOpaque(false);
    }

    private void cleanSignUpPanel() {
        signUpPanel.getFirstNameInput().setText("");
        signUpPanel.getLastNameInput().setText("");
        signUpPanel.getUsernameInput().setText("");
        signUpPanel.getPasswordInput().setText("");
        signUpPanel.getErrorMessage().setText("");
        signUpPanel.getErrorMessage().setOpaque(false);
    }
}