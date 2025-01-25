/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Marks {
    private String studentId;
    private String semester;
    private String subjectId;
    private int marks;

    public Marks(String studentId, String semester, String subjectId, int marks) {
        this.studentId = studentId;
        this.semester = semester;
        this.subjectId = subjectId;
        this.marks = marks;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
    
    // Method to insert marks into the database
    public boolean saveMarks() {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            String sql = "INSERT INTO marks (student_id, semester, subject_id, marks) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, studentId);
            ps.setString(2, semester);
            ps.setString(3, subjectId);
            ps.setInt(4, marks);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateMarks() {
    try {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String sql = "UPDATE marks SET marks = ? WHERE student_id = ? AND subject_id = ? AND semester = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, marks);
        ps.setString(2, studentId);
        ps.setString(3, subjectId);
        ps.setString(4, semester);

        System.out.println("Executing query: " + ps); // Debugging log
        int rowsAffected = ps.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected); // Debugging log
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace(); // Print stack trace for debugging
        return false;
    }
}

    public static ArrayList<String[]> getMarksByStudentId(String studentId) {
        ArrayList<String[]> marksList = new ArrayList<>();

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            String sql = "SELECT semester, subject_id, marks FROM marks WHERE student_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, studentId.trim());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] row = {
                    rs.getString("semester"),
                    rs.getString("subject_id"),
                    String.valueOf(rs.getInt("marks"))
                };
                marksList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return marksList;
    }
}
