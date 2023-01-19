package Controller;

import Model.Task;
import util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskController {

    public static void save(Task task){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO tasks (idProject, name, description, completed, notes, deadline, createdAt, updateAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, task.getIdProject());
            stm.setString(2, task.getName());
            stm.setString(3, task.getDescription());
            stm.setBoolean(4, false);
            stm.setString(5, task.getNotes());
            stm.setString(6, task.getDeadline().toString());
            stm.setString(7, task.getCreatedAt().toString());
            stm.setString(8, LocalDate.now().toString());

            stm.execute();

            ResultSet rst = stm.getGeneratedKeys();
            while (rst.next()) {
                task.setId(rst.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar a task: "+task.getName(),e);
        }
        ConnectionFactory.closeConnection(connection);
    }

    public static void update(Task task){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE tasks SET idProject = ?, name = ?, description = ?, completed = ?, notes = ?, deadline = ?, createdAt = ?, updateAt = ?  WHERE id = ?");
            stm.setInt(1, task.getIdProject());
            stm.setString(2, task.getName());
            stm.setString(3, task.getDescription());
            stm.setBoolean(4, task.isCompletes());
            stm.setString(5, task.getNotes());
            stm.setString(6, task.getDeadline().toString());
            stm.setString(7, task.getCreatedAt().toString());
            stm.setString(8, LocalDate.now().toString());
            stm.setInt(9, task.getId());

            stm.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar a task: "+task.getName(),e);
        }
        ConnectionFactory.closeConnection(connection);
    }

    public static void removeById(int taskId){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM tasks WHERE ID = ?");
            stm.setInt(1, taskId);

            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir task",e);
        }
        ConnectionFactory.closeConnection(connection);
    }

    public static Task selectById(int tasksId){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM tasks WHERE ID = ?");
            stm.setInt(1, tasksId);

            stm.execute();

            ResultSet rst = stm.getResultSet();

            Task task = null;
            if (rst.next()) {
                task = new Task(rst.getInt("id"), rst.getInt("idProject"), rst.getString("name"),
                        rst.getString("description"), rst.getString("notes"), rst.getBoolean("completed"),
                        rst.getDate("deadline").toLocalDate(), rst.getDate("createdAt").toLocalDate(), rst.getDate("updateAt").toLocalDate());
            }

            ConnectionFactory.closeConnection(connection);
            return task;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o id do projeto",e);
        }
    }

    public static List<Task> getAll(int idProject){

        Connection connection = ConnectionFactory.getConnection();

        try {
            List<Task> tasks = new ArrayList<>();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM tasks WHERE idProject = ?");
            stm.setInt(1, idProject);

            stm.execute();

            ResultSet rst = stm.getResultSet();

            while (rst.next()) {
                Task task = new Task(rst.getInt("id"),rst.getInt("idProject"),rst.getString("name"),
                        rst.getString("description"),rst.getString("notes"),rst.getBoolean("completed"),
                        rst.getDate("deadline").toLocalDate(),rst.getDate("createdAt").toLocalDate(),rst.getDate("updateAt").toLocalDate());
                tasks.add(task);
            }
            ConnectionFactory.closeConnection(connection);
            return tasks;
        } catch (SQLException e) {
            ConnectionFactory.closeConnection(connection);
            throw new RuntimeException("Erro ao buscar task do projeto com id: " + idProject, e);
        }
    }

}
