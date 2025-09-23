package models;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentData {
    private final String db = "jdbc:sqlite:sqlite.db";


    public StudentData() {
        try (Connection conn = DriverManager.getConnection(db)) {
            Statement stmt = conn.createStatement();

            stmt.execute("""
                    CREATE TABLE Student(
                        firstName varchar(30),
                        lastName varchar(30),
                        studentId int
                    )
                    """);
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Setters
    public void addStudent(Student student) throws SQLException{
            Connection conn = DriverManager.getConnection(db);
            String query = "INSERT INTO Student (firstName, lastName, studentId) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getStudentID());

            ps.execute();
            ps.close();

    }
    public void removeStudent(Student student){
        try(Connection conn = DriverManager.getConnection(db)){
            String query = "DELETE FROM Student WHERE StudentId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getStudentID());

            ps.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Getters
    public ArrayList<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<Student>();

        try(Connection conn = DriverManager.getConnection(db)){
            String query = "SELECT * from Student";
            Statement stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery(query);

            while(set.next()){
                Student s = new Student(
                  set.getString("firstName"),
                  set.getString("lastName"),
                  set.getInt("studentId")
                );
                students.add(s);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
        return students;
    }
}

