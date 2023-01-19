import Controller.ProjectController;

import Controller.TaskController;
import Model.Project;
import Model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {

        int alternativa = 1;

        Scanner sc = new Scanner(System.in);

        while(alternativa != 0){
            System.out.println("Selecione o que deseja fazer!");
            System.out.println("1-Adicionar Projects");
            System.out.println("2-Adicionar Tasks");
            System.out.println("3-Update Tasks");
            System.out.println("4-Update Projects");
            System.out.println("5-Delete Tasks");
            System.out.println("6-Delete Projects");
            System.out.println("7-Ver projects");
            System.out.println("8-Ver tasks");
            System.out.println("0-Sair");
            alternativa = sc.nextInt();
            sc.nextLine();

            switch (alternativa){
                case 1:

                    System.out.println("Nome do projeto: ");
                    String name = sc.nextLine();
                    System.out.println("Descrição do projeto: ");
                    String description = sc.nextLine();

                    Project project = new Project(null,name,description,LocalDate.now(),LocalDate.now());
                    ProjectController.save(project);

                    break;
                case 2:

                    List<Project> projects = ProjectController.getAll();

                    System.out.println("Projetos:");
                    for (Project value : projects) {
                        System.out.println(value.getId()+", "+value.getName());
                    }

                    System.out.println("Id do projeto");
                    int idProject = sc.nextInt();
                    System.out.println("Nome da tarefa:");
                    sc.nextLine();
                    name = sc.nextLine();
                    System.out.println("Descrição da tarefa:");
                    description = sc.nextLine();
                    System.out.println("Anotações:");
                    String notacoes = sc.nextLine();
                    System.out.println("Data para terminar de fazer (Ex:13/11/2003):");
                    String data = sc.next();

                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formato.parse(data);
                    LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();

                    Task task = new Task(null,idProject ,name, description, notacoes,false, LocalDate.now(), localDate , LocalDate.now());
                    System.out.println(""+task);
                    TaskController.save(task);

                    break;
                case 3:

                    projects = ProjectController.getAll();

                    System.out.println("Projetos:");
                    for (Project value : projects) {
                        System.out.println(value.getId()+", "+value.getName());
                    }

                    System.out.println("Digite o id do projeto:");
                    int idPesquisa = sc.nextInt();

                    List<Task> tasks = TaskController.getAll(idPesquisa);

                    System.out.println("Tarefas:");
                    for (Task value : tasks) {
                        System.out.println(value.getId()+", "+value.getName());
                    }

                    System.out.println("Id da tarefa: ");
                    int idTarefa = sc.nextInt();

                    Task taskUpdate = TaskController.selectById(idTarefa);

                    System.out.println("Selecione o que deseja mudar");
                    System.out.println("1-Id do projeto: "+ taskUpdate.getIdProject());
                    System.out.println("2-Nome do projeto: " +taskUpdate.getName());
                    System.out.println("3-Descrição: " +taskUpdate.getDescription());
                    System.out.println("4-Notas: " +taskUpdate.getNotes());
                    System.out.println("5-Completado: " +taskUpdate.isCompletes());
                    System.out.println("6-Data limite: " +taskUpdate.getDeadline());
                    System.out.println("0-Sair");
                    int selecao = sc.nextInt();
                    sc.nextLine();

                    switch (selecao) {
                        case 1 -> {
                            System.out.println("Digite o novo id do projeto: ");
                            int novoId = sc.nextInt();
                            taskUpdate.setIdProject(novoId);
                            TaskController.update(taskUpdate);
                        }
                        case 2 -> {
                            System.out.println("Digite a nova descrição: ");
                            String novoNome = sc.nextLine();
                            taskUpdate.setName(novoNome);
                            TaskController.update(taskUpdate);
                        }
                        case 3 -> {
                            System.out.println("Digite a nova descrição: ");
                            String novaDescricao = sc.nextLine();
                            taskUpdate.setDescription(novaDescricao);
                            TaskController.update(taskUpdate);
                        }
                        case 4 -> {
                            System.out.println("Digite a nova nota: ");
                            String novaNota = sc.nextLine();
                            taskUpdate.setDescription(novaNota);
                            TaskController.update(taskUpdate);
                        }
                        case 5 -> {
                            System.out.println("Selecione completado ou não");
                            System.out.println("1-Completado");
                            System.out.println("2-Não completado");
                            int selecaoCompletado = sc.nextInt();
                            if (selecaoCompletado == 1) {
                                boolean completadoUpdate = true;
                                taskUpdate.setCompletes(completadoUpdate);
                                TaskController.update(taskUpdate);
                            } else if (selecaoCompletado == 2) {
                                boolean completadoUpdate = false;
                                taskUpdate.setCompletes(completadoUpdate);
                                TaskController.update(taskUpdate);
                            } else {
                                System.out.println("Valor invalido");
                            }
                        }
                        case 6 -> {
                            System.out.println("Nova data para terminar de fazer (Ex:13/11/2003): ");
                            String dataUpdate = sc.nextLine();
                            SimpleDateFormat formatoUpdate = new SimpleDateFormat("dd/MM/yyyy");
                            Date dateUpdate = formatoUpdate.parse(dataUpdate);
                            LocalDate localDateUpdate = dateUpdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            taskUpdate.setDeadline(localDateUpdate);
                            TaskController.update(taskUpdate);
                        }
                        case 0 -> {
                            break;
                        }
                        default -> System.out.println("Digite algo valido");
                    }
                    break;
                case 4:
                    projects = ProjectController.getAll();

                    System.out.println("Projetos:");
                    for (Project value : projects) {
                        System.out.println(value.getId()+", "+value.getName());
                    }

                    System.out.println("Id do Projeto: ");
                    int idProjeto = sc.nextInt();

                    Project projectUpdate = ProjectController.selectById(idProjeto);

                    System.out.println("Selecione o que deseja mudar");
                    System.out.println("1-Nome do projeto" +projectUpdate.getName());
                    System.out.println("2-Descrição" +projectUpdate.getDescription());
                    System.out.println("0-Sair");

                    int selecaoProjeto = sc.nextInt();
                    sc.nextLine();

                    switch (selecaoProjeto) {
                        case 1 -> {
                            System.out.println("Digite o novo nome: ");
                            String novoNome = sc.nextLine();
                            projectUpdate.setName(novoNome);
                            ProjectController.update(projectUpdate);
                        }
                        case 2 -> {
                            System.out.println("Digite a nova descrição: ");
                            String novaDescricao = sc.nextLine();
                            projectUpdate.setDescription(novaDescricao);
                            ProjectController.update(projectUpdate);
                        }
                        case 0 -> {
                            break;
                        }
                        default -> {
                            System.out.println("Digite um valor valido!");
                        }
                    }
                    break;
                case 5:

                    projects = ProjectController.getAll();

                    System.out.println("Projetos:");
                    for (Project value : projects) {
                        System.out.println(value.getId()+", "+value.getName());
                    }

                    System.out.println("Digite o id do projeto:");
                    idPesquisa = sc.nextInt();

                    tasks = TaskController.getAll(idPesquisa);

                    System.out.println("Tarefas:");
                    for (Task value : tasks) {
                        System.out.println(value.getId()+", "+value.getName());
                    }

                    System.out.println("Id da tarefa: ");
                    int idTarefaDelete = sc.nextInt();

                    System.out.println("Certeza que deseja apagar a tarefa: "+TaskController.selectById(idTarefaDelete).getName()+" S ou N");
                    String confirmacao = sc.next();

                    if (Objects.equals(confirmacao, "S")){
                        TaskController.removeById(TaskController.selectById(idTarefaDelete).getId());
                    } else if (Objects.equals(confirmacao, "N")) {
                        System.out.println("Não deletado");
                    }else {
                        System.out.println("Digite um valor valido");
                    }

                    break;
                case 6:
                    projects = ProjectController.getAll();

                    System.out.println("Projetos:");
                    for (Project value : projects) {
                        System.out.println(value.getId()+", "+value.getName());
                    }

                    System.out.println("Id do projeto: ");
                    int idProjetoDelete = sc.nextInt();

                    System.out.println("Certeza que deseja apagar o projeto: "+ProjectController.selectById(idProjetoDelete).getName()+" S ou N");
                    confirmacao = sc.next();

                    if (Objects.equals(confirmacao, "S")){
                        ProjectController.removeById(ProjectController.selectById(idProjetoDelete).getId());
                    } else if (Objects.equals(confirmacao, "N")) {
                        System.out.println("Não deletado");
                    }else {
                        System.out.println("Digite um valor valido");
                    }
                    break;

                case 7:
                    System.out.println("Projetos: " + ProjectController.getAll());

                    break;
                case 8:
                    System.out.println("Id do projeto: ");
                    int id = sc.nextInt();

                    System.out.println("Todos: "+TaskController.getAll(id));

                    break;
            }
        }

    }
}