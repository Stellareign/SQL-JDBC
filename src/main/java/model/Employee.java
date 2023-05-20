package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
 public class Employee {
   City city;
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private Integer cityId;
  //  private Integer cityId = city.getCityId();

    public Employee(String firstName, String lastName, String gender, Integer age, Integer cityId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.cityId = cityId;
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

}
