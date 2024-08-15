import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login implements ActionListener {
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("userID: ");
    JLabel userPasswordLabel = new JLabel("password: ");
    JLabel messageLabel = new JLabel();
    JLabel noid = new JLabel("Don't have an account? Sign Up now: ");
    JButton signup = new JButton("Signup");
    JPanel background;

    Login() {
        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);
        messageLabel.setBounds(125, 250, 480, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        userIDLabel.setBackground(Color.BLACK);
        messageLabel.setBackground(Color.BLACK);

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);
        userIDField.setBackground(Color.YELLOW);
        userPasswordField.setBackground(Color.YELLOW);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);
        loginButton.setBackground(Color.YELLOW);

        resetButton.setBounds(245, 200, 100, 25);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);
        resetButton.setBackground(Color.YELLOW);

        noid.setVisible(true);
        noid.setBounds(60, 300, 250, 35);
        noid.setBackground(Color.black);

        signup.setFocusable(false);
        signup.addActionListener(this);
        signup.setBackground(Color.yellow);
        signup.setBounds(180, 350, 105, 25);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(noid);
        frame.add(signup);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 600);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimensions = toolkit.getScreenSize();
        int x = (screenDimensions.width - frame.getWidth()) / 2;
        int y = (screenDimensions.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signup) {
            signup Sign = new signup();
            frame.dispose();
        } else if (e.getSource() == loginButton) {
            String username = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (authenticate(username, password)) {
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("Login successful!");

            } else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Login failed. Invalid username or password.");
            }
        } else if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }
    }

    private boolean authenticate(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM signup WHERE User_ID = ? AND User_password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) DatabaseConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
