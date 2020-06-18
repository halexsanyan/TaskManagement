package manager;

import db.DBConnectionProvider;
import model.Task;
import model.TaskStatus;
import util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    public UserManager userManager = new UserManager();

    public Task getById(long id) {
        String sql = "SELECT * FROM task WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                return getTaskFromResaltset(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean create(Task task) {
        String sql = "INSERT INTO task(name,description,deadline,status,user_id) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prstatement.setString(1, task.getName());
            prstatement.setString(2, task.getDescription());
            if (task.getDeadline() != null) {
                prstatement.setString(3, DateUtil.convertDateToString(task.getDeadline()));
            } else {
                prstatement.setString(3, null);
            }
            prstatement.setString(4, task.getStatus().name());
            prstatement.setLong(5, task.getUser().getId());
            prstatement.executeUpdate();

            ResultSet rs = prstatement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getLong(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(long id, TaskStatus status) {
        String sql = "UPDATE task SET status = '" + status.name() + "' WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM task WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Task> getAllTasksByUserId(long userId) {
        List<Task> todos = new ArrayList<Task>();
        String sql = "SELECT * FROM task WHERE user_id=?";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            prstatement.setLong(1, userId);
//            prstatement.setString(2, status.name());
            ResultSet resultSet = prstatement.executeQuery();
            while (resultSet.next()) {
                todos.add(getTaskFromResaltset(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    public List<Task> getAll() {
        List<Task> todos = new ArrayList<Task>();
        String sql = "SELECT * FROM task ";
        try {
            PreparedStatement prstatement = connection.prepareStatement(sql);
            ResultSet resultSet = prstatement.executeQuery();
            while (resultSet.next()) {
                todos.add(getTaskFromResaltset(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    private Task getTaskFromResaltset(ResultSet resultSet) {

        try {

                return Task.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .deadline(resultSet.getString(4) == null ? null : DateUtil.convertStringToDate(resultSet.getString(4)))
                        .status(TaskStatus.valueOf(resultSet.getString(5)))
                        .user(userManager.getById(resultSet.getLong(6)))
                        .build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
