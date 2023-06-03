package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
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
//    @OneToMany(mappedBy = "city_id", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Employee> employeesInCity = new ArrayList<>();
  //  private Set<Employee> employees;


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
