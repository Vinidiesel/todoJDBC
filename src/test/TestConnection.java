package test;

import Controller.ProjectController;
import Controller.TaskController;
import Model.Project;
import Model.Task;

import java.time.LocalDate;

public class TestConnection {
    public static void main(String[] args) {


        LocalDate created = LocalDate.now();
        LocalDate deadLine = LocalDate.of(2023, 2, 1);
        LocalDate updateAt = LocalDate.now();

        Task task = new Task(null,1 ,"Fazer Compras", "Ir ao mercado", "Comprar arroz e feijao",false, created, deadLine, updateAt);

        TaskController.save(task);
        task.setCompletes(true);
        task.setDescription("Ir na autoescola");
        TaskController.update(task);
        System.out.println("Todos: "+TaskController.getAll(1));
        TaskController.removeById(task.getId());


        Project project = new Project(null,"Arrumar a casa","Deixar a casa arrumada",created,updateAt);

        ProjectController.save(project);

        project.setUpdateAt(LocalDate.of(2023,1,19));
        ProjectController.update(project);
        System.out.println("Todos: " + ProjectController.getAll());
        ProjectController.removeById(project.getId());


    }
}
