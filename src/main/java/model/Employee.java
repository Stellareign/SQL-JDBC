package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity // персистенс
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  автоматическая генерация ключа
    @Column(name = "id")
    private int id; // примитив не может принимать значение NULL
    @Column(name = "first_name") // аннотацию можно не ставить, если имя колонки таблицы БД и поля класса совпадают
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;
    @Column(name = "city_id")
    private Integer cityId; // Integer может принять значение NULL!

    @ManyToOne
    @JoinColumn(name = "city_id", insertable = false, updatable = false)
           // insertable = false, updatable = false - избавляемся от ошибки
    private City city;

    public Employee(int id, String firstName, String lastName, String gender, int age, Integer cityId) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.cityId = cityId;
    }

    public Employee(String firstName, String lastName, String gender, int age, Integer cityId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.cityId = cityId;
    }

    public Employee(String firstName, String lastName, String gender, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId() == employee.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
