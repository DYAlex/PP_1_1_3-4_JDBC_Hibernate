package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        if (tableExists()) {
            System.err.println("ERROR Creating table: Table already exists");
            return;
        }

        String createString = "CREATE TABLE users" +
                "(id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL, " +
                "last_name VARCHAR(45) NULL, " +
                "age TINYINT(3) NULL, " +
                "PRIMARY KEY (id), " +
                "UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE)";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            System.out.println("Create table ERROR: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        if (!tableExists()) {
            System.err.println("ERROR Dropping table: Table doesn't exist");
            return;
        }
        String deleteString = "DROP TABLE users";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteString);
        } catch (SQLException e) {
            System.out.println("Delete table ERROR: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        if (!tableExists()) {
            System.err.println("ERROR Adding user: Table doesn't exist");
            return;
        }

        String createString = "INSERT INTO users(name, last_name, age) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(createString)) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.out.println("Adding user ERROR: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        if (!tableExists()) {
            System.err.println("ERROR Removing user: Table doesn't exist");
            return;
        }

        String removeString = "DELETE FROM users where id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(removeString)) {
            pstmt.setLong(1, id);
            int success = pstmt.executeUpdate();
            if (success == 0) {
                System.err.println("ERROR removing user. Method returned 0");
            }
        } catch (SQLException e) {
            System.out.println("Delete user ERROR: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        if (!tableExists()) {
            System.err.println("ERROR Getting all users: Table doesn't exist");
            return null;
        }

        List<User> allUsers = new ArrayList<>();
        String getAllUsersString = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement()) {
            boolean success = stmt.execute(getAllUsersString);
            if (success) {
                try (ResultSet rs = stmt.getResultSet()) {
                    while (rs.next()) {
                        User user = new User();

                        user.setId(rs.getLong("id"));
                        user.setName(rs.getString("name"));
                        user.setLastName(rs.getString("last_name"));
                        user.setAge(rs.getByte("age"));

                        allUsers.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR getting all users: " + e.getMessage());
        }

        return allUsers;
    }

    public void cleanUsersTable() {
        if (!tableExists()) {
            System.err.println("ERROR Cleaning all users: Table doesn't exist");
            return;
        }

        String cleanUsersTableString = "TRUNCATE TABLE users";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(cleanUsersTableString);
        } catch (SQLException e) {
            System.out.println("Delete table ERROR: " + e.getMessage());
        }
    }

    private boolean tableExists() {
        DatabaseMetaData md;
        try {
            md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "users", new String[]{"TABLE"});
                return rs.next();
        } catch (SQLException e) {
            System.err.println("ERROR while checking if table exists");
        }
        return false;
    }
}
