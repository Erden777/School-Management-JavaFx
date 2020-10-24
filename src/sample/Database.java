package sample ;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection conn;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement_db?useUnicode=true&serverTimezone=UTC", "root", "");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Teacher> getAllTeachers(){
        ArrayList<Teacher> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from teachers");
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String experience = rs.getString("experience");
                String dob = rs.getString("dob");
                String email = rs.getString("email");
                int subject_id = rs.getInt("subject_id");
                list.add(new Teacher(fname ,lname , dob, username, password , id , experience ,subject_id,email));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addTeacher(Teacher teacher){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO teachers (id  , fname , lname, username, password, experience , dob , email, subject_id) VALUES(?, ?,?,?  ?, ?, ?, ? , ?)");
            ps.setInt(0, teacher.getId());
            ps.setString(1, teacher.getFname());
            ps.setString(3, teacher.getLname());
            ps.setString(4 , teacher.getUsername());
            ps.setString(5 , teacher.getPassword());
            ps.setString(6 , teacher.getExperience());
            ps.setString(7, teacher.getDob());
            ps.setString(8,teacher.getEmail());
            ps.setInt(9,teacher.getSubject_id());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeCar(Long id){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cars where id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
