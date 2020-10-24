package sample;

import java.io.Serializable;

public class RelationManager implements Serializable {
    private String teacher_name;
    private String time;
    private String group_name;

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public RelationManager(String teacher_name, String time, String group_name) {
        this.teacher_name = teacher_name;
        this.time = time;
        this.group_name = group_name;
    }
}
