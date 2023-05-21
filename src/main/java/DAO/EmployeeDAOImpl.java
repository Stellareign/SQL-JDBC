package DAO;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    //  private final Connection connection;

    int employeeId = 1; // переменная для запросов

    // ДОБАВЛЕНИЕ СУЩНОСТИ В БД:
    @Override

    public void addEmployeeToDatabase(Employee employee) {
        // В ресурсах блока try создаем объект сессии с помощью нашего конфиг-файла
        // И открываем сессию
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            // Создаем транзакцию и начинаем ее
            Transaction transaction = session.beginTransaction();
            // вызываем на объекте сессии метод save
            // данный метод внутри себя содержит необходимый запрос к базе
            // для создания новой строки
            session.save(employee);
            // Выполняем коммит, то есть сохраняем изменения,
            // которые совершили в рамках транзакции
            transaction.commit();
        }
    }

    // ПОИСК ПО ID:
    @Override
    public Employee findEmployeeById(int id) {
        // С помощью конфиг-файла получаем сессию, открываем ее
        // и через метод get получаем объект
        // В параметре метода get нужно указать объект какого класса нам нужен
        // и его id
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
    }


    // ВЕСЬ СПИСОК:
    @Override
    public List<Employee> employeeListFromDatabase() {
        List<Employee> employeeList = (List<Employee>)
                HibernateSessionFactoryUtil     // обращение к фабрике
                        .getSessionFactory()    // получение фабрики сессий и соотв.класса
                        .openSession()          //открывается новая сессия openSession()
                        .createQuery("From Employee") // запрос на получение всех объектов Employee
                        .list();                // сохраняем всё в лист
        return employeeList;                    // и возвращаем список
    }


    //ИЗМЕНЕНИЕ В БД:
    @Override
    public void updateEmployeeInDatabase(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
                                                // Для обновления данных нужно передать в конструктор
                                                 // объект с актуальными данными (включая id)
            session.update(employee);
            transaction.commit();
        }
    }

    // УДАЛЕНИЕ ИЗ БД:
    @Override
    public void deleteEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Для удаления объекта из таблицы нужно передать его в метод delete (включая id)
            session.delete(employee);
            transaction.commit();
        }
    }

    // УДАЛЕНИЕ ИЗ БД по ID:
    @Override
    public void deleteEmployeeById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Employee employee = session.get(Employee.class, id); // Получаем объект Employee по id
            Transaction transaction = session.beginTransaction();
            session.delete(employee); // Удаляем полученный по id объект из базы данных
            transaction.commit();
        } catch (Exception e) { // Обрабатываем возможные исключения

        }
    }
}

