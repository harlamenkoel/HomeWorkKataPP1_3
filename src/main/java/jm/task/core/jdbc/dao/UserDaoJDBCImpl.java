package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.getConnection()) {
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS user" +
                    "(id bigint not null auto_increment PRIMARY KEY," +
                    "name varchar(45) not null," +
                    "lastName varchar(45) not null," +
                    "age int not null)").executeUpdate();
            System.out.println("Произошло создание таблицы");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.prepareStatement("DROP TABLE IF EXISTS user").executeUpdate();
            System.out.println("Произошл удаление таблицы");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO user(name,lastname,age) VALUES(?, ?, ?)");
            System.out.println("User с именем – " + name + " добавлен в базу данных");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setLong(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Произошло удаление user под ID - " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM user ");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getLong("age"));

                userList.add(user);
            }
            System.out.println(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public void cleanUsersTable() {

        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user");
            preparedStatement.executeUpdate();
            System.out.println("Произошла отчистка таблицы");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
