package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

/*
Необходимо ознакомиться с заготовкой и доработать приложение,
которое взаимодействует с базой оперируя пользователем ( класс User )
и проверить свои методы заранее написанными JUnit тестами.
По итогу все тесты должны быть пройдены. Разрешается посмотреть реализацию тестов.

Для запуска теста необходимо найти класс в папке test ( показано в предыдущей лекции )
и при нажатии на него правой кнопкой мыши запустить, выбрав Run "Имя класса"

Класс UserHibernateDaoImpl в рамках этой задачи не затрагивается (остаётся пустой)

User представляет из себя сущность с полями:

Long id
String name
String lastName
Byte age
Архитектура приложения создана с опорой на паттерн проектирования MVC ( частично, у нас не WEB приложение)

Требования к классам приложения:

1. Классы dao/service должны реализовывать соответствующие интерфейсы
2. Класс dao должен иметь конструктор пустой/по умолчанию
3. Все поля должны быть private
4. service переиспользует методы dao
5. Обработка всех исключений, связанных с работой с базой данных должна находиться в dao
+. Класс Util должен содержать логику настройки соединения с базой данных


Необходимые операции:

1. Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
2. Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
3. Очистка содержания таблицы
4. Добавление User в таблицу
5. Удаление User из таблицы ( по id )
6. Получение всех User(ов) из таблицы


Алгоритм работы приложения:

В методе main класса Main должны происходить следующие операции:

1. Создание таблицы User(ов)
2. Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
3. Получение всех User из базы и вывод в консоль (+ должен быть переопределен toString в классе User)
4. Очистка таблицы User(ов)
5. Удаление таблицы
 */
public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
//        UserDao userDao = new UserDaoJDBCImpl();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);
//        userService.removeUserById(1);

//        userService.getAllUsers();

        List<User> users = userService.getAllUsers();
        users.stream().forEach(user -> System.out.println(user));
//        users.stream().forEach(user -> System.out.printf("User has id %d. User's name is %s %s. User is %d years old\n", user.getId(), user.getName(), user.getLastName(), user.getAge()));

        userService.cleanUsersTable();
        userService.dropUsersTable();

//        userDao.createUsersTable();
//
//        userDao.saveUser("Name1", "LastName1", (byte) 20);
//        userDao.saveUser("Name2", "LastName2", (byte) 25);
//        userDao.saveUser("Name3", "LastName3", (byte) 31);
//        userDao.saveUser("Name4", "LastName4", (byte) 38);
//
//        userDao.removeUserById(1);
//        userDao.removeUserById(1);
//
//        List<User> users = userDao.getAllUsers();
//        users.stream().forEach(user -> System.out.printf("User has id %d. User's name is %s %s. User is %d years old\n", user.getId(), user.getName(), user.getLastName(), user.getAge()));
//
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
    }
}
