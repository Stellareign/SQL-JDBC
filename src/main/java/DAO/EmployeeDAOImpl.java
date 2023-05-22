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
        }// автоматическое закрытие сессии в блоке трай
    }

    // ПОИСК ПО ID:
    @Override
    public Employee findEmployeeById(int id) {
        // С помощью конфиг-файла получаем сессию, открываем ее
        // и через метод get получаем объект
        // В параметре метода get нужно указать объект какого класса нам нужен
        // и его id
        try (Session session = // try-with-resources для автоматического закрытия сессии -> передаём объект в скобки
                     HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }// автоматическое закрытие сессии в блоке трай
    }


    // ВЕСЬ СПИСОК:
    @Override
    public List<Employee> employeeListFromDatabase() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) { // try-with-resources
            //   List<Employee> employeeList = (List<Employee>)
            return session.createQuery("From Employee") // запрос на получение всех объектов Employee
                    .list(); // и возвращаем список
        }// автоматическое закрытие сессии в блоке трай
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
        }// автоматическое закрытие сессии в блоке трай
    }

    // УДАЛЕНИЕ ИЗ БД:
    @Override
    public void deleteEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Для удаления объекта из таблицы нужно передать его в метод delete (включая id)
            session.delete(employee);
            transaction.commit();
        }// автоматическое закрытие сессии в блоке трай
    }

    // УДАЛЕНИЕ ИЗ БД по ID:
    @Override
    public void deleteEmployeeById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Employee employee = session.get(Employee.class, id); // Получаем объект Employee по id
            Transaction transaction = session.beginTransaction();
            session.delete(employee); // Удаляем полученный по id объект из базы данных
            transaction.commit();
        } // автоматическое закрытие сессии в блоке трай
    }

    // тест-метод (от наставника):
    public void test() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Employee employee = session.get(Employee.class, 1);// вызов метода get() у объекта Session для поиска сотрудника с id=1.
            employee.setLastName("UPS"); //  изменение фамилии найденного сотрудника.
            session.get(Employee.class, 2); // вызов метода get() у объекта Session для поиска сотрудника с id=2 (но результат не используется).
            session.update(employee); // вызов метода update() у объекта Session для обновления данных о сотруднике с измененной фамилией
        } // автоматическое закрытие сессии в блоке трай
    }
}

