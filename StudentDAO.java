// JDBC CRUD Coding Question
// Write a Java class StudentDAO that performs CRUD operations on a student table.
// Table Structure:
// id (int, primary key)
// name (varchar)
// email (varchar)
// Requirements:
// 1. Create (INSERT)
// Implement method:
// Java
// public boolean addStudent(int id, String name, String email)
// 2. Read (SELECT)
// Implement methods:
// Java
// public void getStudentById(int id)

// public void getAllStudents()
// 3. Update
// Implement method:
// Java
// public boolean updateStudentEmail(int id, String newEmail)
// 4. Delete
// Implement method:
// Java
// public boolean deleteStudent(int id)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    // 1. CREATE
    public boolean addStudent(int id, String name, String email) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "root");
        PreparedStatement ps = con.prepareStatement("INSERT INTO student VALUES (?, ?, ?)");

        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, email);

        int i = ps.executeUpdate();

        if (i > 0) {
            System.out.println("Success");
            return true;
        } else {
            System.out.println("Failure");
            return false;
        }
    }

    // 2. READ BY ID
    public void getStudentById(int id) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "root");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM student WHERE id = ?");

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println(
                rs.getInt("id") + " " +
                rs.getString("name") + " " +
                rs.getString("email")
            );
        } else {
            System.out.println("Student not found");
        }
    }

    // 3. READ ALL
    public void getAllStudents() throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "root");

        PreparedStatement ps = con.prepareStatement("SELECT * FROM student");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + " " +
                rs.getString("name") + " " +
                rs.getString("email")
            );
        }
    }

    // 4. UPDATE
    public boolean updateStudentEmail(int id, String newEmail) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "root");
        PreparedStatement ps = con.prepareStatement("UPDATE student SET email = ? WHERE id = ?");

        ps.setString(1, newEmail);
        ps.setInt(2, id);

        int i = ps.executeUpdate();

        if (i > 0) {
            System.out.println("Success");
            return true;
        } else {
            System.out.println("Failure");
            return false;
        }
    }

    // 5. DELETE
    public boolean deleteStudent(int id) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "root");
        PreparedStatement ps = con.prepareStatement("DELETE FROM student WHERE id = ?");

        ps.setInt(1, id);

        int i = ps.executeUpdate();

        if (i > 0) {
            System.out.println("Success");
            return true;
        } else {
            System.out.println("Failure");
            return false;
        }
    }
}