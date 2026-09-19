package task.lesson7;

public class Task {
    public String title;
    public String completed;

    public Task(String title, String completed){
        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public String getCompleted() {
        return completed;
    }
}
