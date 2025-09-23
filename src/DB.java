import models.Student;

import java.sql.*;

public class DB {
    public static void main(String[] args) {
        String db = "jdbc:sqlite:sqlite.db";

        try(Connection conn = DriverManager.getConnection(db)){
            Statement stmt = conn.createStatement();

            stmt.execute("""
                    CREATE TABLE Student(
                        firstName varchar(30),
                        lastName varchar(30),
                        studentId int
                    )
                    """);
            stmt.close();

            Student s = new Student("Paul","Royer", 12345);

            String query = "INSERT INTO Student (firstName, lastName, studentId) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setInt(3, s.getStudentID());

            ps.execute();
            ps.close();

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
