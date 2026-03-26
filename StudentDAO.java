import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {
    
    public boolean addStudent(int id, String name, String email) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "root");
        PreparedStatement ps = con.prepareStatement("INSERT INTO student VALUES (?, ?, ?)");
        ps.setInt(1,id);
        ps.setString(2,name);
        ps.setString(3,email);
        int i= ps.executeUpdate();

        if(i>0) {
            System.out.println("Success");
            return true;
        }
        else{
            System.out.println("Failure");
            return false;
        }
    }

    public void getStudentById(int id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "root");
        PreparedStatement ps= con.prepareStatement("SELECT * FROM student WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs= ps.executeQuery();
        if (rs.next()) {
            System.out.println(rs.getInt("id")+rs.getString("name")+rs.getString("email"));
        }
        else{
            System.out.println("Failure");
        }
    }

    public void getAllStudents() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "root");
        PreparedStatement ps= con.prepareStatement("SELECT * FROM student");
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt("id")+rs.getString("name")+rs.getString("email"));
        }
    }

    public boolean updateStudentEmail(int id, String newEmail) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "root");
        PreparedStatement ps= con.prepareStatement("UPDATE student SET email = ? WHERE id = ?");
        ps.setString(1,newEmail);
        ps.setInt(2,id);
        int i= ps.executeUpdate();
        if(i>0) {
            System.out.println("Success");
            return true;
        } 
        else {
            System.out.println("Failure");
            return false;
        }
    }

    
    public boolean deleteStudent(int id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "root");
        PreparedStatement ps= con.prepareStatement("DELETE FROM student WHERE id = ?");
        ps.setInt(1, id);
        int i= ps.executeUpdate();
        if(i>0) {
            System.out.println("Success");
            return true;
        }
        else{
            System.out.println("Failure");
            return false;
        }
    }
}
