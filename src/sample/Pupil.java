package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Pupil extends Human  implements Serializable{
    private int id;
    private ArrayList<Subject> subjects = null;
    private int group_id;
    private String country;

    public Pupil(int id, int group_id, String country) {
        this.id = id;
        this.group_id = group_id;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Pupil(int group_id, String country) {
        this.group_id = group_id;
        this.country = country;
    }

    public Pupil(String fname, String lname, String dob, String username, String password, int group_id, String country) {
        super(fname, lname, dob, username, password);
        this.group_id = group_id;
        this.country = country;
    }

    public Pupil(ArrayList<Subject> subjects, int group_id, String country) {
        this.subjects = subjects;
        this.group_id = group_id;
        this.country = country;
    }

    public Pupil(String fname, String lname, String dob, String username, String password, ArrayList<Subject> subjects, int group_id, String country) {
        super(fname, lname, dob, username, password);
        this.subjects = subjects;
        this.group_id = group_id;
        this.country = country;
    }

    public Pupil(String fname, String lname, String dob, String username, String password, int id, int group_id, String country) {
        super(fname, lname, dob, username, password);
        this.id = id;
        this.group_id = group_id;
        this.country = country;
    }

    public Pupil(int id, ArrayList<Subject> subjects, int group_id, String country) {
        this.id = id;
        this.subjects = subjects;
        this.group_id = group_id;
        this.country = country;
    }

    public Pupil(String fname, String lname, String dob, String username, String password, int id, ArrayList<Subject> subjects, int group_id, String country) {
        super(fname, lname, dob, username, password);
        this.id = id;
        this.subjects = subjects;
        this.group_id = group_id;
        this.country = country;
    }
}