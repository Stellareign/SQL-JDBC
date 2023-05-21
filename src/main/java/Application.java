import DAO.EmployeeDAOImpl;
import model.Employee;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

        // ДОБАВЛЕНИЕ В БД:
        Employee employee1 = new Employee("Maxim", "Andreev", "male", 23, 1);
        //  employeeDAO.addEmployeeToDatabase(employee1);

        // УДАЛЕНИЕ из БД:
        Employee employee2 = new Employee(12, "Maxim", "Andreev", "male", 23, 1);
//        employeeDAO.deleteEmployee(employee2);

        // УДАЛЕНИЕ ПО ID:
     //   employeeDAO.deleteEmployeeById(14);

        // ВЕСЬ СПИСОК
        // employeeDAO.employeeListFromDatabase().stream().forEach(System.out::println);

        // ПОЛУЧЕНИЕ ПО id:
    //    System.out.println(employeeDAO.findEmployeeById(1));

        // ОБНОВЛЕНИЕ:
        Employee employee3 = new Employee(7, "Marina", "Tihomolova", "female", 43, 1);
       employeeDAO.updateEmployeeInDatabase(employee3);


    }
}
