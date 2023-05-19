package DAO;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    Employee findEmployeeById(Integer id);


    List<Employee> employeeListFromDatabase();

    void updateEmployeeInDatabase(Integer id, Employee employee) throws SQLException;
}
