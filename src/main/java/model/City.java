package model;

import lombok.*;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Data
@Getter // вместо @Data лучше использовать @Getter и @Setter в JPA
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // персистенс
@Table(name = "city")
public class City {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) //  автоматическая генерация ключа
   @Column(name = "city_id")
    private Integer id;
    @Column(name = "city_name")
    private String cityName;
//
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employeesInCity = new ArrayList<>();
   // private Set<Employee> employees;


    public City(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {

        return "City{" +
                "id=" + (id != null ? id : null) +
                ", cityName='" + (cityName != null ? cityName : null) + '\'' +
                '}';
    }
    public static City createCity(ResultSet resultSet) throws SQLException { // метод для создания объекта по результату запроса
       City city = new City();
        city.setId(resultSet.getInt("city_id")); // извлекаем значения полей
        city.setCityName(resultSet.getString("city_name"));
        return city; // возвращаем созданный объект
    }
}
