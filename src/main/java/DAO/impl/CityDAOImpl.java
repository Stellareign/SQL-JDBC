package DAO.impl;

import DAO.api.CityDAO;
import Factory.HibernateSessionFactoryUtil;
import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAOImpl implements CityDAO {

    // ДОБАВЛЕНИЕ ГОРОДА
    @Override
    public void addNewCity(City city) { // CREATE
        try (
                Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }// автоматическое закрытие сессии в блоке трай
    }

    // ПОИСК ГОРОДА ПО ИД
    @Override
    public City findCityById(Integer city_id) { // READ
        try (Session session =
                     HibernateSessionFactoryUtil.getSessionFactory().openSession()) { // транзакция для данной операции не обязательна (не нужна)
            return session.get(City.class, city_id);

        }
    }

    // ПРОСМОТР СПИСКА ГОРОДОВ
    @Override
    public List<City> allCityList() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) { // try-with-resources
            return session.createQuery("From City")
                    .list();
        }// автоматическое закрытие сессии в блоке трай
    }

    // ОБНОВЛЕНИЕ ДАНЫХ ГОРОДА - поиск по id
    @Override
    public void updateCityInDatabaseByID(int id, City updCity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
//            City city = session.find(City.class, id); // находим город по указанному id
//            city.setCityName(updCity.getCityName()); // присваиваем ему заданное значение
//            session.update(city); // обновляем объект в БД
//            transaction.commit(); // фиксируем изменения и завершаем транзакцию
            updCity.setId(id);
            session.merge(updCity); // либо заменит город с указанным ID, если id нет в базе, добавит новый город в БД
            transaction.commit();// фиксируем изменения и завершаем транзакцию

        }// автоматическое закрытие сессии в блоке трай
    }

    // УДАЛЕНИЕ ГОРОДА ПО ИД
    @Override
    public void deleteCityById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            City city = session.get(City.class, id);
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        } // автоматическое закрытие сессии в блоке трай
    }

}
