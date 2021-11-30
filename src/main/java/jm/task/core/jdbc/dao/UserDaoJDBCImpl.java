package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("CREATE TABLE `testbase`.`users` (`id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL, `lastName` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (id))");
        } catch (SQLException throwables) {

        }

    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("DROP TABLE `testbase`.`users`");
            System.out.println("Таблица удалена");
        } catch (SQLException throwables) {

        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = Util.getConnection().prepareStatement(
                "INSERT INTO users(name, lastName, age) VALUES(?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = Util.getConnection().prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User с id – " + id + " удален из данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet res = statement.executeQuery("SELECT * FROM users");
            List<User> userList = new ArrayList<>();
            while (res.next()) {
                long id = res.getLong("id");
                String name = res.getString("name");
                String lastName = res.getString("lastName");
                byte age = res.getByte("age");
                userList.add(new User(name, lastName, age));
            }
            return userList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("TRUNCATE TABLE users");
            System.out.println("Таблица очищена");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
