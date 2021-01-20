package hiutrun.example.todoapplication.model;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.util.Date;

public class Task {
    private String taskName;
    private String description;
    private String date;
    private String time;
    private boolean status;

    public Task() {
    }

    public Task(String taskName, @Nullable String description, String date, String time, boolean status) {
        this.description = description;
        this.taskName = taskName;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
