package DAO;

import model.City;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAOImpl implements CityDAO{

    public void addNewCity (City city){ // CREATE
      try (
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
        Transaction transaction = session.beginTransaction();
        session.save(city);
        transaction.commit();
    }// автоматическое закрытие сессии в блоке трай
}

    public City findeCityById (Integer city_id) { // READ
        try (Session session =
                     HibernateSessionFactoryUtil.getSessionFactory().openSession()) { // транзакция для данной операции не обязательна (не нужна)
            return session.get(City.class, city_id);

        }
    }
//    public List<City> allEmployeeInCytyByCityId (Integer city_id) {  // READ
//        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) { // try-with-resources
//            //   List<Employee> employeeList = (List<Employee>)
//            return session.createQuery("From City were") // запрос на получение всех объектов Employee (можно сделать любой "усложнённый" запрос)
//                    .list(); // и возвращаем список
//    }
//    public City correctCity (){} // UPDATE
//    public void deleteCityFromDataBase(){} // DELETE

}
