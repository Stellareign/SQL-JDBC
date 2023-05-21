package DAO;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    // ДОБАВЛЕНИЕ СУЩНОСТИ В БД:
    void addEmployeeToDatabase(Employee employee);

    Employee findEmployeeById(Integer id);


    List<Employee> employeeListFromDatabase();

    // ВЕСЬ СПИСОК с JOIN:
    List<Employee> employeeListFromDatabaseWithJoin();

    // ВЕСЬ СПИСОК C парсингом:
   // List<Employee> employeeListFromDatabaseWithJoinAndPars();

    void updateEmployeeInDatabase(Integer id, Employee employee) ;

    // УДАЛЕНИЕ ИЗ БД:
    void deleteEmployeeById(Integer id);
}
