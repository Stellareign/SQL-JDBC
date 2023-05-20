import DAO.EmployeeDAOImpl;
import model.Employee;

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
//        System.out.println("****** ПОИСК ПО ID: ******");
//        System.out.println(employeeDAO.findEmployeeById(2));
//
        System.out.println("****** ВЕСЬ СПИСОК без JOIN и парсинга: ******");
        employeeDAO.employeeListFromDatabase().stream().forEach(System.out::println);

        System.out.println("****** ВЕСЬ СПИСОК с применением JOIN ******");

        employeeDAO.employeeListFromDatabaseWithJoin().stream().forEach(System.out::println);

        System.out.println("***** c JOIN b парсом весь список****");
        employeeDAO.employeeListFromDatabaseWithJoinAndPars().stream().forEach(System.out::println);
//
//        System.out.println("****** ДОБАВЛЕНИЕ НОВОЙ СУЩНОСТИ В ДБ: ******");
//        Employee employee = new Employee("Ekaterina", "Razumova", "female", 41, 1); // создаём сущность
//        employeeDAO.addEmployeeToDatabase(employee); // добавляем
//        employeeDAO.employeeListFromDatabase().stream().forEach(System.out::println); // смотрим весь список

        System.out.println("******** ИЗМЕНЕНИЕ СУЩНОСТИ В БД: *****");

      //  employee.setLastName("Mohova");
//        Employee employee2 = employeeDAO.findEmployeeById(1);
//        employee2.setFirstName("Anastasiia");
//        employee2.setLastName("Rudneva");
//        employeeDAO.updateEmployeeInDatabase(1, employee2);
//        System.out.println(employeeDAO.findEmployeeById(1));

//        System.out.println("******** УДАЛЕНИЕ СУЩНОСТИ ИЗ БД: *****");
//        employeeDAO.deleteEmployeeById(10);





    }
}
