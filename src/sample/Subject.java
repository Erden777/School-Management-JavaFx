package sample;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.io.Serializable;



public class Subject implements Serializable {
    private int id;
    private String subname;
    private int teacher_id;

    public Subject(int id, String subname, int teacher_id) {
        this.id = id;
        this.subname = subname;
        this.teacher_id = teacher_id;
    }

    public Subject() {
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

}
