package DAO;

import model.City;
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

    // ДОБАВЛЕНИЕ СУЩНОСТИ В БД:
    @Override
    public void addEmployeeToDatabase(Employee employee) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO employee (first_name, last_name," +
                    " gender, age, city_id) VALUES ((?), (?), (?), (?), (?))");
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCityId());
            statement.executeUpdate();
        } catch (SQLException e) { // ловим ошибки
            throw new RuntimeException(e);
        }
    }

    // ПОИСК ПО ID:
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


    // ВЕСЬ СПИСОК:
    @Override
    public List<Employee> employeeListFromDatabase() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee")) {
            ResultSet resultSet = preparedStatement.executeQuery(); // запрос к БД с помощью метода executeQuery(), который возвращает ResultSet
            List<Employee> employeeList = new ArrayList<>(); //создаём лист
            while (resultSet.next()) {
                //if (!resultSet.wasNull()) {// пока следующая строка не пуста
                    employeeList.add(Employee.create(resultSet)); //добавляем в лист объект из запроса
              //  } else employeeList.add(Employee.createWithCity(resultSet));
            }
            return employeeList; // возвращаем список
        } catch (SQLException e) { // ловим ошибки
            throw new RuntimeException(e);
        }
    }

    // ВЕСЬ СПИСОК с JOIN без парсинга:
    @Override
    public List<Employee> employeeListFromDatabaseWithJoin() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee INNER JOIN city ON city.city_id=employee.city_id")) {
            ResultSet resultSet = preparedStatement.executeQuery(); // запрос к БД с помощью метода executeQuery(), который возвращает ResultSet
            List<Employee> employeeList = new ArrayList<>(); //создаём лист
            while (resultSet.next()) { // пока следующая строка не пуста
                employeeList.add(Employee.createWithCity(resultSet)); //добавляем в лист объект из запроса
            }
            return employeeList; // возвращаем список
        } catch (SQLException e) { // ловим ошибки
            throw new RuntimeException(e);
        }
    }

    // ВЕСЬ СПИСОК C парсингом:
    @Override
    public List<Employee> employeeListFromDatabaseWithJoinAndPars() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee FULL OUTER JOIN city ON city.city_id=employee.city_id")) {
            ResultSet resultSet = preparedStatement.executeQuery(); // запрос к БД с помощью метода executeQuery(), который возвращает ResultSet
            List<Employee> employeeList = new ArrayList<>(); //создаём лист
            while (resultSet.next()) { // пока следующая строка в запросе не пуста
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityId = resultSet.getInt("city_id");
                String cityName = resultSet.getString("city_name");
                //  City city = null;
                if (!resultSet.wasNull()) {
                    City city = new City(cityId, cityName);
                    employeeList.add(new Employee(id, firstName, lastName, gender, age, city));
                } else {
                    City city = new City(null, null);
                employeeList.add(new Employee(id, firstName, lastName, gender, age, city));
                }
            }
            return employeeList; // возвращаем список
        } catch (SQLException e) { // ловим ошибки
            throw new RuntimeException(e);
        }
    }

    //ИЗМЕНЕНИЕ В БД:
    @Override
    public void updateEmployeeInDatabase(Integer id, Employee employee) {
        try {

            PreparedStatement statement = connection.prepareStatement("UPDATE employee SET first_name = (?), " +
                    "last_name = (?), gender = (?), age = (?), city_id = (?) WHERE id = (?)");
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCityId());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) { // ловим ошибки
            throw new RuntimeException(e);
        }
    }

    // УДАЛЕНИЕ ИЗ БД:
    @Override
    public void deleteEmployeeById(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE  FROM employee WHERE id = (?)");
            statement.setInt(employeeId, id);
            statement.executeUpdate();
            employeeListFromDatabase().stream().forEach(System.out::println);
        } catch (SQLException e) { // ловим ошибки
            throw new RuntimeException(e);
        }
    }
}

