package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);

        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();

//        Util.closeConnection();

        // Added in main for testing dao hibernate
//        UserDao userDaoHibernate = new UserDaoHibernateImpl();
//        userDaoHibernate.createUsersTable(); // works
//
//        userDaoHibernate.saveUser("Name1", "LastName1", (byte) 20); // works
//        userDaoHibernate.saveUser("Name2", "LastName2", (byte) 25);
//        userDaoHibernate.saveUser("Name3", "LastName3", (byte) 31);
//        userDaoHibernate.saveUser("Name4", "LastName4", (byte) 38);
//
//        userDaoHibernate.removeUserById(1); // works
//        userDaoHibernate.removeUserById(1); // works
//
//        List<User> users = userDaoHibernate.getAllUsers(); // works
//        users.forEach(System.out::println);
//
//        userDaoHibernate.cleanUsersTable(); // works
//
//        List<User> usersAfterClean = userDaoHibernate.getAllUsers();
//        if (usersAfterClean.size() == 0) {
//            System.out.println("Users table cleaned SUCCESS");
//        } else {
//            System.out.println("Users table clean FAIL");
//            usersAfterClean.forEach(System.out::println);
//        }
//
//        userDaoHibernate.dropUsersTable(); // works
    }
}
