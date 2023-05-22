package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class City {
    private Integer cityId;
    private String cityName;

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + (cityId != null ? cityId : null) +
                ", cityName='" + (cityName != null ? cityName : null) + '\'' +
                '}';
    }
}
