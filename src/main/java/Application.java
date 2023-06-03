import DAO.impl.CityDAOImpl;
import DAO.impl.EmployeeDAOImpl;
import model.City;
import model.Employee;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        CityDAOImpl cityDAO = new CityDAOImpl();

        // ДОБАВЛЕНИЕ В БД:
        Employee employee1 = new Employee("Elena", "Gordeeva", "female", 27 );
        employeeDAO.addEmployeeToDatabase(employee1);

        // УДАЛЕНИЕ из БД:
        Employee employee2 = new Employee(12, "Maxim", "Andreev", "male", 23, 1);
//        employeeDAO.deleteEmployee(employee2);

        // УДАЛЕНИЕ ПО ID:
        //   employeeDAO.deleteEmployeeById(14);

        // ВЕСЬ СПИСОК
        employeeDAO.employeeListFromDatabase().stream().forEach(System.out::println);

        // ПОЛУЧЕНИЕ ПО id:
        //    System.out.println(employeeDAO.findEmployeeById(1));

        // ОБНОВЛЕНИЕ:
        Employee employee3 = new Employee(7, "Marina", "Tihomolova", "female", 43, 1);
        //  employeeDAO.updateEmployeeInDatabase(employee3);

        // ******************************* ГОРОДА**************************** //
        // ДДОБАЛЕНИЕ ГОРОДА:
        City city = new City("Irkutsk");
      //   cityDAO.addNewCity(city);

        // ОБНОВЛЕНИЕ ГОРОДА:
          cityDAO.updateCityInDatabaseByID(5, new City("Kostroma"));

        // УДАЛЕНИЕ ГОРОДА:
        //   cityDAO.deleteCityById(5);

        // СПИСОК ГОРОДОВ:
            cityDAO.allCityList().stream().forEach(System.out::println);


    }
}
