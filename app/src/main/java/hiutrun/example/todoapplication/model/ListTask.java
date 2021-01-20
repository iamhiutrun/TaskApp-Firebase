package hiutrun.example.todoapplication.model;

import java.util.List;

public class ListTask {
    public List<Task> taskList;

    public ListTask(List<Task> taskList) {
        this.taskList = taskList;
    }

    public ListTask() {
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
