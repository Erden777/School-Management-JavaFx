package sample;

import java.io.Serializable;

public abstract class Human implements Serializable {
    private String fname;
    private String lname;
    private String dob;
    private String username;
    private String password;


    public Human(){};

    public Human(String fname, String lname, String dob, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
