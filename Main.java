import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        new Login();


    }
}