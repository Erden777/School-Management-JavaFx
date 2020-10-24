package sample;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.xdevapi.SqlDataResult;


public class ClientHandler extends Thread {

    private Socket socket;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private Connection conn;

    public ClientHandler(Socket socket, Connection conn) {
        this.conn = conn;
        this.socket = socket;

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            Request req = null;
            try {
                req = (Request) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (req.getCode().equals("ADDGROUP")) {
                Group group = new Group(req.getFname());
                addGroup(group);
                Reply reply = new Reply();

                if (group == null) {
                    reply.setCode("NOT_REGISTRATION");
                } else {
                    reply.setGroup(group);
                    reply.setCode("ADDED SUCCESSFULLY");
                }

                try {
                    oos.writeObject(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if (req.getCode().equals("ADDTEACHERGROUP")) {
                int id =0;
                TeacherGroup teacherGroup = new TeacherGroup(id , req.getId() , req.getGroup_id());
                addTeacherGroup(teacherGroup);
                Reply reply = new Reply();

                if (teacherGroup == null) {
                    reply.setCode("NOT_REGISTRATION");
                } else {
                    reply.setTeacherGroup(teacherGroup);
                    reply.setCode("ADDED SUCCESSFULLY");
                }

                try {
                    oos.writeObject(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if (req.getCode().equals("ADDTIMETABLE")) {
                Schedule schedule = new Schedule(req.getId(),req.getFname(),req.getGroup_id());
                addTimeTable(schedule);
                Reply reply = new Reply();

                if (schedule == null) {
                    reply.setCode("NOT_REGISTRATION");
                } else {
                    reply.setSchedule(schedule);
                    reply.setCode("ADDED SUCCESSFULLY");
                }
                try {
                    oos.writeObject(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (req.getCode().equals("VIEWTIMETABLE")) {
                Reply rep = new Reply();
                List<Schedule> schedules = getAllTimetable();
                rep.setSchedules(schedules);
                try {
                    oos.writeObject(rep);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (req.getCode().equals("DELETETEACHERGROUP")) {
                int id =0;
                deleteGroupTeacher(req.getId(),req.getGroup_id());
                Reply reply = new Reply();
                reply.setTeacherGroups(getAllTeacherGroups());
                reply.setCode("Deleted SUCCESSFULLY");

                try {
                    oos.writeObject(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if (req.getCode().equals("VIEWTEACHERS")) {
                Reply rep = new Reply();
                ArrayList<Teacher> teachers = getAllTeachers();

                for (Teacher t : teachers) {
                    rep.addTeacher(t);
                }

                try {
                    oos.writeObject(rep);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else if (req.getCode().equals("VIEWPUPILS")) {
                Reply rep = new Reply();
                ArrayList<Pupil> pupils = getAllPupils();
                rep.setPupils(pupils);

                try {
                    oos.writeObject(rep);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else if (req.getCode().equals("VIEWTEACHERGROUP")) {
                Reply rep = new Reply();
                List<TeacherGroup> teacherGroups = getAllTeacherGroups();
                rep.setTeacherGroups(teacherGroups);
                try {
                    oos.writeObject(rep);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (req.getCode().equals("VIEWALLSUBJECTS")) {
                Reply rep = new Reply();
                List<Subject> subjects = getAllSubjects();
                rep.setSubjects(subjects);
                try {
                    oos.writeObject(rep);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (req.getCode().equals("VIEWALLGROUPS")) {
                Reply rep = new Reply();
                List<Group> groups = getAllGroup();
                rep.setGroups(groups);
                try {
                    oos.writeObject(rep);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else if(req.getCode().equals("LOGIN")){
                Pupil pupil = getPupil(req.getLogin(), req.getPassword());
                Reply reply = new Reply();

                if (pupil == null) {
                    reply.setCode("NOT_PUPIL");
                } else {
                    reply.setPupil(pupil);
                    reply.setCode("LOGIN_IN");
                }
                try {
                    oos.writeObject(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            else if(req.getCode().equals("TEACHERLOGIN")){
                Teacher teacher = getTeacher(req.getLogin(), req.getPassword());
                Reply reply = new Reply();

                if (teacher == null) {
                    reply.setCode("NOT_PUPIL");
                } else {
                    reply.setTeacher(teacher);
                    reply.setCode("LOGIN_IN");
                }
                try {
                    oos.writeObject(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (req.getCode().equals("REGISTRATIONPUPIL")) {
                Pupil pupil = new Pupil(req.getFname(),req.getLname(),req.getDOB(),req.getLogin(),req.getPassword(),req.getGroup_id(),req.getCountry());
                registerPupil(pupil);
                Reply reply = new Reply();

                if (pupil == null) {
                    reply.setCode("NOT_REGISTRATION");
                } else {
                    reply.setPupil(pupil);
                    reply.setCode("ADDED SUCCESSFULLY");
                }

                try {
                    oos.writeObject(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            else if (req.getCode().equals("REGISTRATIONTEACHER")) {
                Teacher teacher = new Teacher(req.getFname(),req.getLname(),req.getDOB(),req.getLogin(),req.getPassword(),req.getExperience(),req.getSubject_id(),req.getEmail());
                registerTeacher(teacher);
                Reply reply = new Reply();
                reply.setTeacher(teacher);
                reply.setCode("ADDED SUCCESSFULLY");
                try {
                    oos.writeObject(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            else if(req.getCode().equals("ADMINLOGIN")){
                Admin admin = getAdmin(req.getLogin(), req.getPassword());
                Reply reply = new Reply();
                if (admin == null) {
                    reply.setCode("NOT_ADMIN");
                } else {
                    reply.setAdmin(admin);
                    reply.setCode("LOGIN_IN");
                }
                try {
                    oos.writeObject(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



            if (req.getCode().equals("EXIT")) {
                try {
                    oos.close();
                    ois.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void addTimeTable(Schedule schedule) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO schedule (teacher_id, time , group_id) VALUES(?, ?, ?)");
            ps.setInt(1, schedule.getTeacher_id());
            ps.setString(2, schedule.getTime());
            ps.setInt(3, schedule.getGroup_id());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Schedule> getAllTimetable() {
        List<Schedule> schedules = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from schedule");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String time = rs.getString("time");
                int teacher_id = rs.getInt("teacher_id");
                int group_id = rs.getInt("group_id");
                schedules.add(new Schedule(teacher_id,time, group_id));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

    private void addTeacherGroup(TeacherGroup teacherGroup) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO teacher_group (id, teacher_id , group_id) VALUES(?, ?, ?)");
            ps.setInt(1, teacherGroup.getId());
            ps.setInt(2, teacherGroup.getTeacher_id());
            ps.setInt(3, teacherGroup.getGroup_id());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteGroupTeacher(int teacher_id , int group_id){
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM teacher_group where teacher_id = ? and group_id = ? ");
            ps.setInt(1,teacher_id);
            ps.setInt(2,group_id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private List<TeacherGroup> getAllTeacherGroups() {
        List<TeacherGroup> teacherGroups = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from teacher_group");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int teacher_id = rs.getInt("teacher_id");
                int group_id = rs.getInt("group_id");
                teacherGroups.add(new TeacherGroup(id , teacher_id, group_id));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherGroups;


    }


    private ArrayList<Teacher> getAllTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from teachers");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String experience = rs.getString("experience");
                String dob = rs.getString("dob");
                String email = rs.getString("email");
                int subject_id = rs.getInt("subject_id");

                teachers.add(new Teacher(fname, lname, dob, username, password, id, experience, subject_id, email));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    private ArrayList<Pupil> getAllPupils() {
        ArrayList<Pupil> pupils = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from pupils");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String country = rs.getString("country");
                String dob = rs.getString("dob");
                int group_id = rs.getInt("group_id");

                pupils.add(new Pupil(fname, lname, dob, username, password, id, group_id, country));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pupils;
    }
    private List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from subjects");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String subj_name = rs.getString("subj_name");
                int teacher_id = rs.getInt("teacher_id");
                subjects.add(new Subject(id,subj_name,teacher_id));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    private List<Group> getAllGroup() {
        List<Group> groups = new ArrayList<>();
        System.out.println("Group");
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from groups");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String subj_name = rs.getString("group_name");

                groups.add(new Group(subj_name,id));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groups;
    }

    public void addSubject(Subject subject){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO subjects (id, subj_name ,teacher_id) VALUES(?, ?, ?, ?)");
            ps.setInt(1, subject.getId());
            ps.setString(2, subject.getSubname());
            ps.setInt(3, subject.getTeacher_id());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGroup(Group group) {
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO groups (id, group_name) VALUES(null, ?)");
            ps.setString(1, group.getName());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    private void registerPupil(Pupil pupil) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO pupils(id, fname,lname, username , password , country , dob, group_id) VALUES(NULL, ?, ?, ?,?,?,?,?)");
            ps.setString(1, pupil.getFname());
            ps.setString(2, pupil.getLname());
            ps.setString(3, pupil.getUsername());
            ps.setString(4, pupil.getPassword());
            ps.setString(5, pupil.getCountry());
            ps.setString(6, pupil.getDob());
            ps.setInt(7, pupil.getGroup_id());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }


    private Pupil getPupil(String username, String password) {
        Pupil pupil = null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM pupils WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String country = rs.getString("country");
                String dob = rs.getString("dob");
                int group_id = rs.getInt("group_id");
                pupil = new Pupil(fname, lname, dob, username, password, id, group_id, country);
            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pupil;
    }

    private Admin getAdmin(String username, String password) {
        Admin admin = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admins WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                System.out.println(id);
                admin = new Admin(id , username, password);
            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    private void registerTeacher(Teacher teacher) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO teachers(id, fname,lname, username , password , experience , dob , email , subject_id) VALUES(null, ?, ?, ?,?,?,?,? ,?)");
            ps.setString(1, teacher.getFname());
            ps.setString(2, teacher.getLname());
            ps.setString(3, teacher.getUsername());
            ps.setString(4, teacher.getPassword());
            ps.setString(5, teacher.getExperience());
            ps.setString(6, teacher.getDob());
            ps.setString(7, teacher.getEmail());
            ps.setInt(8, teacher.getSubject_id());


            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Teacher getTeacher(String username, String password) {
        Teacher teacher = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM teachers WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String experience = rs.getString("experience");
                String dob = rs.getString("dob");
                int subject_id = rs.getInt("subject_id");
                String email = rs.getString("email");
                teacher = new Teacher(fname, lname, dob, username, password, id, experience, subject_id, email);
            }

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }


  /*  public void removeCar(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cars where id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

*/

}

