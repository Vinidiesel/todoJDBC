package Controller;

import Model.Project;
import util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectController {

    public static void save(Project project){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO projects (name, description, createdAt, updateAt) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, project.getName());
            stm.setString(2, project.getDescription());
            stm.setString(3, project.getCreatedAt().toString());
            stm.setString(4, LocalDate.now().toString());

            stm.execute();

            ResultSet rst = stm.getGeneratedKeys();
            while (rst.next()) {
                project.setId(rst.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar a projeto: "+project.getName(),e);
        }
        ConnectionFactory.closeConnection(connection);
    }

    public static void update(Project project){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE projects SET name = ?, description = ?, createdAt = ?, updateAt = ?  WHERE id = ?");
            stm.setString(1, project.getName());
            stm.setString(2, project.getDescription());
            stm.setString(3, project.getCreatedAt().toString());
            stm.setString(4, LocalDate.now().toString());
            stm.setInt(5, project.getId());

            stm.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar a projeto: "+project.getName(),e);
        }
        ConnectionFactory.closeConnection(connection);
    }

    public static void removeById(int projectId){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM projects WHERE ID = ?");
            stm.setInt(1, projectId);

            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir projeto",e);
        }
        ConnectionFactory.closeConnection(connection);
    }

    public static Project selectById(int projectId){
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM projects WHERE ID = ?");
            stm.setInt(1, projectId);
            stm.execute();

            ResultSet rst = stm.getResultSet();

            Project project = null;
            if (rst.next()) {
                project = new Project(rst.getInt("id"), rst.getString("name"),
                        rst.getString("description"), rst.getDate("createdAt").toLocalDate(), rst.getDate("updateAt").toLocalDate());
            }

            ConnectionFactory.closeConnection(connection);
            return project;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o id do projeto",e);
        }
    }

    public static List<Project> getAll(){

        Connection connection = ConnectionFactory.getConnection();

        try {
            List<Project> projects = new ArrayList<>();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM projects");

            stm.execute();

            ResultSet rst = stm.getResultSet();

            while (rst.next()) {
                Project project = new Project(rst.getInt("id"),rst.getString("name"),
                        rst.getString("description"), rst.getDate("createdAt").toLocalDate(),rst.getDate("updateAt").toLocalDate());
                projects.add(project);
            }
            ConnectionFactory.closeConnection(connection);
            return projects;
        } catch (SQLException e) {
            ConnectionFactory.closeConnection(connection);
            throw new RuntimeException("Erro ao buscar os projetos", e);
        }
    }
}
