package sample;


import java.io.Serializable;

public class Request  implements Serializable {
    private String code;
    private Teacher teacher;
    private Pupil pupil;
    private Admin admin;
    private int id;
    private String login;
    private String password;
    private String email;
    private String fname;
    private String lname;
    private String experience;
    private String DOB;
    private String country;
    private int group_id;
    private int subject_id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getLogin() {
        return login;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Request() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Request(String code) {
        this.code = code;
    }
    public Request(String code, int id){
        this.code = code;
        this.id = id;
    }

    public Request(String code, Teacher teacher) {
        this.code = code;
        this.teacher = teacher;
    }

    public Request(String code, Teacher teacher, Pupil pupil) {
        this.code = code;
        this.teacher = teacher;
        this.pupil = pupil;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Teacher getTeacher() {
        return teacher;
    }


    public Pupil getPupil() {
        return pupil;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    @Override
    public String toString() {
        return "Request{" +
                "code='" + code + '\'' +
                ", teacher=" + teacher +
                ", pupil=" + pupil +
                ", id=" + id +
                '}';
    }
}
