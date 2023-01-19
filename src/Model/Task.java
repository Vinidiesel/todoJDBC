package Model;

import java.time.LocalDate;

public class Task {

    private Integer id;
    private int idProject;
    private String name;
    private String description;
    private String notes;
    private boolean completes;
    private LocalDate deadline;
    private LocalDate createdAt;
    private LocalDate updateAt;

    public Task(Integer id, int idProject, String name, String description, String notes, boolean completes, LocalDate deadline, LocalDate createdAt, LocalDate updateAt) {
        this.id = id;
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.completes = completes;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCompletes() {
        return completes;
    }

    public void setCompletes(boolean completes) {
        this.completes = completes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", idProject=" + idProject +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                ", completes=" + completes +
                ", deadline=" + deadline +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                "}\n";
    }
}
