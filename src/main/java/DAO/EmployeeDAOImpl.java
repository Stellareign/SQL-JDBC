package DAO;

import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final Connection connection;

    int employeeId = 1; // переменная для запросов

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public void addEmployeeToDatabase(Employee employee) {

    }


    @Override
    public Employee findEmployeeById(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)")) {
            preparedStatement.setInt(employeeId, id); // задали значение запроса (id)
            preparedStatement.setMaxRows(1); // максимальное количество строк в результате заспроса (1)
            ResultSet resultSet = preparedStatement.executeQuery(); // запрос к БД с помощью метода executeQuery(), который возвращает ResultSet
            resultSet.next(); // перемещаем указатель на первую строку результата
            return Employee.create(resultSet); // создаём объект по результату запроса (из метода в классе Employee)
        } catch (SQLException e) { // ловим ошибки
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> employeeListFromDatabase() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee")) {
            ResultSet resultSet = preparedStatement.executeQuery(); // запрос к БД с помощью метода executeQuery(), который возвращает ResultSet
            List<Employee> employeeList = new ArrayList<>(); //создаём лист
            while (resultSet.next()) { // пока следующая строка не пуста
                employeeList.add(Employee.create(resultSet)); //добавляем в лист объект из запроса
            }
            return employeeList; // возвращаем список
        } catch (SQLException e) { // ловим ошибки
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEmployeeInDatabase(Integer id, Employee employee) throws SQLException {
        //  Employee employee = findEmployeeById(employeeId);
       PreparedStatement statement = connection.prepareStatement("UPDATE employee SET first_name = (?), " +
                "last_name = (?), gender = (?), age = (?), city_id = (?) WHERE id = (?)");
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCityId());
            statement.setInt(6, id);
            statement.executeUpdate();
        //    preparedStatement.setInt(6, employee.getId());
            // return Employee.create(resultSet);

    }

//
//    public Employee deleteEmployeeById(Integer id) {
//
}

