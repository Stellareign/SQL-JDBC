package model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
 public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private Integer cityId;
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
}
