package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Connector {
    private Socket socket = null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private ObservableList<Teacher> teachers;
    private ObservableList<Pupil> pupils;

    public ObservableList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ObservableList<Group> groups) {
        this.groups = groups;
    }

    private ObservableList<Subject> subjects;
    private ObservableList<Group> groups;


    public ObservableList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ObservableList<Subject> subjects) {
        this.subjects = subjects;
    }

    public Connector() {
        teachers = FXCollections.observableArrayList();
        pupils = FXCollections.observableArrayList();
        subjects = FXCollections.observableArrayList();
        groups =FXCollections.observableArrayList();

        try {
            socket = new Socket("127.0.0.1", 1999);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            Scanner in = new Scanner(System.in);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        Request request = new Request();
        request.setCode("EXIT");
        try {
            oos.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Teacher> getTeachers(){
        return teachers;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }


    public void addGroup(String name ){
        Request request = new Request();
        request.setCode("ADDGROUP");
        request.setFname(name);
        Reply reply;
        try{

            oos.writeObject(request);
            System.out.println(request.getCode());
            reply = (Reply)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteTeacherGroup(int teacher_id, int group_id ){
        Request request = new Request();
        request.setCode("DELETETEACHERGROUP");
        request.setId(teacher_id);
        request.setGroup_id(group_id);
        Reply reply ;
        try{

            oos.writeObject(request);

            reply = (Reply)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Teacher registerTeacher(String username, String password, String fname, String lname , String ecperience , String DOB, String email , int subj_id) {
        Request request = new Request();
        System.out.println("hi");
        request.setCode("REGISTRATIONTEACHER");
        request.setLogin(username);
        request.setPassword(password);
        request.setFname(fname);
        request.setLname(lname);
        request.setExperience(ecperience);
        request.setDOB(DOB);
        request.setEmail(email);
        request.setSubject_id(subj_id);
        Reply reply;
        try {

            oos.writeObject(request);
            System.out.println(request.getCode());
            reply = (Reply)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return reply.getTeacher();
    }

    public Pupil registerPupil(String username, String password, String fname, String lname , String country , String DOB , int group_id) {
        Request request = new Request();
        request.setCode("REGISTRATIONPUPIL");
        request.setLogin(username);
        request.setPassword(password);
        request.setFname(fname);
        request.setLname(lname);
        request.setCountry(country);
        request.setDOB(DOB);
        request.setGroup_id(group_id);
        Reply reply;
        try {
            System.out.println("connector");
            oos.writeObject(request);

            reply = (Reply) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return reply.getPupil();
    }

    public void setTeachers(ObservableList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ObservableList<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(ObservableList<Pupil> pupils) {
        this.pupils = pupils;
    }

    public List<Teacher> getTeachersDb() {
        Request request =new Request();
        Reply reply;
        request.setCode("VIEWTEACHERS");
        try{
            oos.writeObject(request);
            reply = (Reply)ois.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            return null;
        }
        return reply.getTeachers();
    }


    public List<Subject>getSubjectsDb(){
        Request request =new Request();
        request.setCode("VIEWALLSUBJECTS");
        Reply reply;
        try{
            oos.writeObject(request);
            reply = (Reply)ois.readObject();

        }
        catch (IOException | ClassNotFoundException e){
            return null;
        }

        subjects.setAll(reply.getSubjects());
        return reply.getSubjects();
    }

    public List<TeacherGroup> getTeacherGroupDb(){
        Request request =new Request();
        request.setCode("VIEWTEACHERGROUP");
        Reply reply;
        try{
            oos.writeObject(request);
            reply = (Reply)ois.readObject();

        }
        catch (IOException | ClassNotFoundException e){
            return null;
        }
        return reply.getTeacherGroups();
    }

    public List<Group> getAllGroupDb(){
        Request request =new Request();
        request.setCode("VIEWALLGROUPS");
        Reply reply;
        try{
            oos.writeObject(request);
            reply = (Reply)ois.readObject();

        }
        catch (IOException | ClassNotFoundException e){
            return null;
        }

        System.out.println("Connector");
        groups.setAll(reply.getGroups());
        return reply.getGroups();
    }

    public List<Pupil>getPupilDb(){
        System.out.println("Get All pupil");
        Request request = new Request();
        request.setCode("VIEWPUPILS");
        Reply reply;

        try{
            oos.writeObject(request);
            reply = (Reply)ois.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            return null;
        }
        pupils.setAll(reply.getPupils());
        return reply.getPupils();
    }

    public Pupil PupilLogin(String username , String password){
        Request request =new Request();
        request.setCode("LOGIN");
        request.setLogin(username);
        request.setPassword(password);
        Reply reply;
        try {
            oos.writeObject(request);
            reply = (Reply) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        if (reply.getCode().equals("NOT_PUPIL")) {
            return null;
        }
        return reply.getPupil();
    }

    public Teacher TeacherLogin(String username , String password){
        Request request=new Request();
        request.setCode("TEACHERLOGIN");
        request.setLogin(username);
        request.setPassword(password);
        Reply reply;
        try {
            oos.writeObject(request);
            reply = (Reply) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        if (reply.getCode().equals("NOT_TEACHER")) {
            return null;
        }
        System.out.println(reply.getCode());
        return reply.getTeacher();
    }

    public Admin AdminLogin(String username, String password) {
        Request request = new Request();
        request.setCode("ADMINLOGIN");
        request.setLogin(username);
        request.setPassword(password);
        Reply reply;
        try {
            oos.writeObject(request);
            reply = (Reply) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        if (reply.getCode().equals("NOT_ADMIN")) {
            return null;
        }
        return reply.getAdmin();
    }

    public void addTeacherGroup(int teacherid, int groupid) {
        Request request = new Request();
        request.setCode("ADDTEACHERGROUP");
        request.setId(teacherid);
        request.setGroup_id(groupid);
        Reply reply;
        try{

            oos.writeObject(request);
            System.out.println(request.getCode());
            reply = (Reply)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void addTimetable(int teacher_id,String time ,int groupid) {
        Request request = new Request();
        request.setCode("ADDTIMETABLE");
        request.setId(teacher_id);
        request.setGroup_id(groupid);
        request.setFname(time);
        Reply reply;
        try{

            oos.writeObject(request);
            System.out.println(request.getCode());
            reply = (Reply)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Schedule> getAllTimeTableDb(){
        Request request =new Request();
        request.setCode("VIEWTIMETABLE");
        Reply reply;
        try{
            oos.writeObject(request);
            reply = (Reply)ois.readObject();

        }
        catch (IOException | ClassNotFoundException e){
            return null;
        }
        return reply.getSchedules();
    }


}
