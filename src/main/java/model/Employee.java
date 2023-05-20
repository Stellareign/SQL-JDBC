package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private Integer cityId;
    private String cityName;
    private City city;

    //  private Integer cityId = city.getCityId();

    public Employee(String firstName, String lastName, String gender, Integer age, Integer cityId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.cityId = cityId;
    }

    // вариант для джойна без парсинга
    public Employee(Integer id, String firstName, String lastName, String gender, Integer age, Integer cityId, String cityName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    // конструктор с ГОРОДОМ
    public Employee(Integer id, String firstName, String lastName, String gender, Integer age, City city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public static Employee create(ResultSet resultSet) throws SQLException { // метод для создания объекта по результату запроса
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id")); // извлекаем значения полей
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setGender(resultSet.getString("gender"));
        employee.setAge(resultSet.getInt("age"));
        employee.setCityId(resultSet.getInt("city_Id"));
        return employee; // возвращаем созданный объект
    }

    // Второй вариант для JOIN:
    public static Employee createWithCity(ResultSet resultSet) throws SQLException { // метод для создания объекта по результату запроса
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id")); // извлекаем значения полей
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setGender(resultSet.getString("gender"));
        employee.setAge(resultSet.getInt("age"));
        employee.setCityId(resultSet.getInt("city_Id"));
        employee.setCityName(resultSet.getString("city_name"));
        return employee; // возвращаем созданный объект
    }

    @Override
    public String toString() { // изменила тустринг, чтобы оставить варианты конструктора и обеспечить корректный вывод без null, там где это не нужно
        if (city != null) {
            return "Employee{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", gender='" + gender + '\'' +
                    ", age=" + age +
                    ", city=" + city + '\'' +
                    '}';
        } else if (cityName != null) {
            return
            "Employee{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", gender='" + gender + '\'' +
                    ", age=" + age +
                    ", cityId=" + cityId +
                    ", cityName='" + cityName + '\'' +
                    '}';
        } else return  "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age + ", cityId=" + cityId + '}';
    }
}
