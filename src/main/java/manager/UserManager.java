package manager;


import db.DBConnectionProvider;
import model.User;
import model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public User getById(long id) {
        String sql = "SELECT * FROM user WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                return getUserFromResaltset(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO user(name,surname,email,password) VALUES('" + user.getName() + "','" +
                    user.getSurname() + "','" + user.getEmail() + "','" + user.getPassword() + "');";
            System.out.println("exequting the folowing statment ->" + sql);
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                user.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public User getByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email =?";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            prstatement.setString(1, email);
            ResultSet resultSet = prstatement.executeQuery();
            if (resultSet.next()) {

                return getUserFromResaltset(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email =? AND password=?";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            prstatement.setString(1, email);
            prstatement.setString(2, password);
            ResultSet resultSet = prstatement.executeQuery();
            if (resultSet.next()) {

                return getUserFromResaltset(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM user";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            ResultSet resultSet = prstatement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResaltset(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE id=" + id;
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private User getUserFromResaltset(ResultSet resultSet) {

        try {
            return User.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .password(resultSet.getString(5))
                    .userType(UserType.valueOf(resultSet.getString(6)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
