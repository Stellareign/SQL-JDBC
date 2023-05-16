import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
       final String  user = "postgres";
       final String password = "1234";
       final String url = "jdbc:postgresql://localhost:5432/skypro";

        final Connection connection = DriverManager.getConnection(url, user, password);

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)");
    }
}
