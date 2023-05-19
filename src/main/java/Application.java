import DAO.EmployeeDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "1234";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

//       try (Connection connection = DriverManager.getConnection(url, user, password);
//           Statement statement = connection.createStatement() // для запросов к БД
//
//                   ) {
//           ResultSet resultSet = statement.executeQuery("SELECT * FROM employee WHERE id = (1)");
//           while (resultSet.next()) {
//               System.out.println(resultSet);
//           }
//       }catch (SQLException e){
//           throw new RuntimeException(e);
//       }
//    }
        Connection connection = DriverManager.getConnection(url, user, password);
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl(connection);
        System.out.println(employeeDAO.findEmployeeById(1));
        System.out.println("******");
        employeeDAO.employeeListFromDatabase().stream().forEach(System.out::println);
        System.out.println("******");


    }
}
