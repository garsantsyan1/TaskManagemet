package model.manager;

import db.DBConnectionProvider;
import model.Status;
import model.Task;



import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void add(Task task) {
        String sql1 = "INSERT INTO task(name,description,status,deadline,user_id) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus().name());
            ps.setString(4,sdf.format(task.getDeadline()));
            ps.setInt(5, task.getUserID());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                task.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Task> getTasksByUserId(int userId) {
        String sql = "SELECT * FROM task where user_id = " + userId;
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setName(resultSet.getString(2));
                task.setDescription(resultSet.getString(3));
                task.setStatus(Status.valueOf(resultSet.getString(4)));
                task.setDeadline(resultSet.getDate(5));
                task.setUserID(resultSet.getInt(6));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }


    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM task";
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(getTaskFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void deleteTaskById(int id) {
        String sql = "DELETE * FROM task where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Task getTaskFromResultSet(ResultSet resultSet) {
        try {
            return Task.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .status(Status.valueOf(resultSet.getString(4)))
                    .deadline(resultSet.getDate(5))
                    .userID(resultSet.getInt(6))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateTaskStatusById(int id, Status status) {
        String sql = "UPDATE task_management.task t SET t.status = '" + status + "' WHERE t.id = " + id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserID(int id) {
        String sql = "UPDATE task_management.task t SET t.user_id = null WHERE t.user_id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeUserId(int userId, int taskId) {
        String sql = "UPDATE task_management.task t SET t.user_id = " + userId + " WHERE t.id = " + taskId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
