package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private Integer age;
    @Column(name = "city_id")
    private Integer cityId;
    @Column(name = "city_name")
    private String cityName;



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

         if (cityName != null) {
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
        } else return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age + ", cityId=" + cityId + '}';
    }
}
