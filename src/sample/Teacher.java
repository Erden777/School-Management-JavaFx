package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Teacher extends Human implements Serializable {
    private int id;
    private String experience;
    private Subject subject;
    private int subject_id ;
    private String email;

    public Teacher(int id, String experience, Subject subject, String email) {
        this.id = id;
        this.experience = experience;
        this.subject = subject;
        this.email = email;
    }

    public Teacher(String experience, int subject_id, String email) {
        this.experience = experience;
        this.subject_id = subject_id;
        this.email = email;
    }

    public Teacher(String fname, String lname, String dob, String username, String password, String experience, int subject_id, String email) {
        super(fname, lname, dob, username, password);
        this.experience = experience;
        this.subject_id = subject_id;
        this.email = email;
    }

    public Teacher(String fname, String lname, String dob, String username, String password, int id, String experience, Subject subject, String email) {
        super(fname, lname, dob, username, password);
        this.id = id;
        this.experience = experience;
        this.subject = subject;
        this.email = email;
    }

    public Teacher(String fname, String lname, String dob, String username, String password, int id, String experience, int subject_id, String email) {
        super(fname, lname, dob, username, password);
        this.id = id;
        this.experience = experience;
        this.subject_id = subject_id;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
