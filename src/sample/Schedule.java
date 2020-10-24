package sample;

import java.io.Serializable;

public class Schedule implements Serializable {
    private int teacher_id;
    private String time;
    private int group_id;

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public Schedule(int teacher_id, String time, int group_id) {
        this.teacher_id = teacher_id;
        this.time = time;
        this.group_id = group_id;
    }
}
