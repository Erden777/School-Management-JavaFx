package sample;

import java.io.Serializable;

public class TeacherGroup implements Serializable {
    private int id;
    private int teacher_id;
    private int group_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public TeacherGroup(int id, int teacher_id, int group_id) {
        this.id = id;
        this.teacher_id = teacher_id;
        this.group_id = group_id;
    }
}
