package sample;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reply  implements Serializable {
    private String code;
    private Pupil pupil;
    private Group group;
    private Teacher teacher;
    private Admin admin;
    private TeacherGroup teacherGroup;
    private Schedule schedule;

    public TeacherGroup getTeacherGroup() {
        return teacherGroup;
    }

    public void setTeacherGroup(TeacherGroup teacherGroup) {
        this.teacherGroup = teacherGroup;
    }

    private ArrayList<Teacher> teachers = null;
    private ArrayList<Pupil> pupils = null;
    private List<Subject> subjects = null;
    private List<Group> groups = null;
    private List<TeacherGroup> teacherGroups= null;
    private List<Schedule> schedules=null;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setPupils(ArrayList<Pupil> pupils) {
        this.pupils = pupils;
    }

    public Reply(String code, ArrayList<Teacher> teachers, ArrayList<Pupil> pupils, List<Subject> subjects,List<Group> groups,List<Schedule> schedules) {
        this.code = code;
        this.teachers = teachers;
        this.pupils = pupils;
        this.subjects = subjects;
        this.schedules =schedules;
    }

    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Reply(String code, Pupil pupil, Group group, Teacher teacher, Admin admin, ArrayList<Teacher> teachers, ArrayList<Pupil> pupils, List<Subject> subjects, List<Group> groups, List<TeacherGroup> teacherGroups) {
        this.code = code;
        this.pupil = pupil;
        this.group = group;
        this.teacher = teacher;
        this.admin = admin;
        this.teachers = teachers;
        this.pupils = pupils;
        this.subjects = subjects;
        this.groups = groups;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


    public Reply() {
        teachers = new ArrayList<>();
        pupils = new ArrayList<>();
        subjects = new ArrayList<>();
        groups = new ArrayList<>();
        teacherGroups = new ArrayList<>();
        schedules = new ArrayList<>();
    }

    public Reply(String code) {
        this.code = code;
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }
    public void addPupil(Pupil pupil){
        pupils.add(pupil);

    }

    public List<TeacherGroup> getTeacherGroups() {
        return teacherGroups;
    }

    public void setTeacherGroups(List<TeacherGroup> teacherGroups) {
        this.teacherGroups = teacherGroups;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSubject(Subject subject){
        subjects.add(subject);
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<Pupil> getPupils() {
        return pupils;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
