import javax.sql.rowset.RowSetWarning;
import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
       final String  user = "postgres";
       final String password = "1234";
       final String url = "jdbc:postgresql://localhost:5432/skypro";

       try (Connection connection = DriverManager.getConnection(url, user, password);
           Statement statement = connection.createStatement() // для запросов к БД
                   ) {
           ResultSet resultSet = statement.executeQuery("SELECT * FROM employee WHERE id = (1)");
           while (resultSet.next()) {
               System.out.println(resultSet);

               //   PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)"); // для запросов к базе

           }
       }catch (SQLException e){
           throw new RuntimeException(e);
       }
    }
}
