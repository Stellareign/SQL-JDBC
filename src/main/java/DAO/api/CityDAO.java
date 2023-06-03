package DAO.api;

import model.City;

import java.util.List;

public interface CityDAO {
    // ДОБАВЛЕНИЕ ГОРОДА
    void addNewCity(City city);

    // ПОИСК ГОРОДА ПО ИД
    City findCityById(Integer city_id);

    // ПРОСМОТР СПИСКА ГОРОДОВ
    List<City> allCityList();

    // ОБНОВЛЕНИЕ ДАНЫХ ГОРОДА - поиск по id
    void updateCityInDatabaseByID(int id, City updCity);

    // УДАЛЕНИЕ ГОРОДА ПО ИД
    void deleteCityById(int id);
}
