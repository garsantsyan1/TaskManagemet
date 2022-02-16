package manager;

import db.DBConnectionProvider;
import model.Status;
import model.Task;
import model.Type;
import model.User;

import java.io.ObjectInputFilter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Task task) {
        String sql1 = "INSERT INTO task(name,description,status,deadline,user_id) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus().name());
            ps.setDate(4, (Date) task.getDeadline());
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

}
