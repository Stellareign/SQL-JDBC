package DAO.api;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {

    // ДОБАВЛЕНИЕ СУЩНОСТИ В БД:
    void addEmployeeToDatabase(Employee employee);

    // ПОИСК ПО ID:
    Employee findEmployeeById(int id);

    // ВЕСЬ СПИСОК:
    List<Employee> employeeListFromDatabase();


    //ОБНОВЛЕНИЕ СУЩНОСТИ:
    void updateEmployeeInDatabase(Employee employee);

    // УДАЛЕНИЕ ИЗ БД:
    void deleteEmployee(Employee employee);

    void deleteEmployeeById(int id);
}
