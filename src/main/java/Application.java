import DAO.impl.CityDAOImpl;
import DAO.impl.EmployeeDAOImpl;
import Factory.PersistenceUtil;
import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
//        EntityManagerFactory entityManagerFactory = Persistence
//                .createEntityManagerFactory("myPersistenceUnit");
        EntityManagerFactory entityManagerFactory = PersistenceUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory
                .createEntityManager();
// операции с БД
        entityManager.getTransaction().begin();
        Employee employee1 = new Employee("Mariia", "Varlamova", "female", 46, 1);
        Employee employee = new Employee("Elena", "Gordeeva", "female", 27);

        // ДОБАВЛЕНИЕ СУЩНОСТИ:
//        entityManager.persist(employee1);

        //УДАЛЕНИЕ СУЩНОСТИ:
//        Employee employee2 = entityManager.find(Employee.class, 21);
//        entityManager.remove(employee2);

        // СПИСОК ВСЕХ:
        List<Employee> employees = entityManager
                .createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

        for (Employee employee3 : employees) {
            System.out.println(employee3);
        }

        entityManager.getTransaction().commit();
        //
        entityManager.close();
        entityManagerFactory.close();

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        CityDAOImpl cityDAO = new CityDAOImpl();
    }
    // ДОБАВЛЕНИЕ В БД:
//        Employee employee1 = new Employee("Elena", "Gordeeva", "female", 27);
    //       employeeDAO.addEmployeeToDatabase(employee1);
//
//        // УДАЛЕНИЕ из БД:
//      //  Employee employee2 = new Employee(12, "Maxim", "Andreev", "male", 23, 1);
////        employeeDAO.deleteEmployee(employee2);
//
//        // УДАЛЕНИЕ ПО ID:
//        //   employeeDAO.deleteEmployeeById(14);
//
//        // ВЕСЬ СПИСОК
//        employeeDAO.employeeListFromDatabase().stream().forEach(System.out::println);
//
//        // ПОЛУЧЕНИЕ ПО id:
//        //    System.out.println(employeeDAO.findEmployeeById(1));
//
//        // ОБНОВЛЕНИЕ:
//     //   Employee employee3 = new Employee(7, "Marina", "Tihomolova", "female", 43, 1); // нерабочий после удаления поля ID
//        //  employeeDAO.updateEmployeeInDatabase(employee3);
//
//        // ******************************* ГОРОДА**************************** //
//        // ДДОБАЛЕНИЕ ГОРОДА:
//        City city = new City("Irkutsk");
//        //   cityDAO.addNewCity(city);
//
//        // ОБНОВЛЕНИЕ ГОРОДА:
//        //     cityDAO.updateCityInDatabaseByID(5, new City("Kostroma"));
//
//        // УДАЛЕНИЕ ГОРОДА:
//        //   cityDAO.deleteCityById(5);
//
//        // СПИСОК ГОРОДОВ:
//        cityDAO.allCityList().stream().forEach(System.out::println);
//
//        // CСПИСОК СОТРУДНИКОВ В ГОРОДЕ:
//        cityDAO.getAllEmployeesByCityId(2).stream().forEach(System.out::println);

}

