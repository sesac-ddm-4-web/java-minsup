package task.lesson7;

public class Task {
    // 1. 변수의 접근제어자를 private로 지정 (외부 직접 접근 불가)
    private String title;
    private String completed;

    public Task(String title, String completed) {
        this.title = title;
        this.completed = completed;
    }

    // 2. 외부에서 값을 읽을 수 있도록 Getter 제공
    public String getTitle() {
        return title;
    }

    public String getCompleted() {
        return completed;
    }

    // 3. 외부에서 완료 상태를 변경할 수 있도록 Setter 제공
    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
