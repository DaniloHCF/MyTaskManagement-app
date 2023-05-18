package task.category;

public class TaskCategory {
    private String name;

    TaskCategory workTaskCategory = new TaskCategory("Trabalho");
    TaskCategory personalTaskCategory = new TaskCategory("Pessoal");

    public TaskCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
