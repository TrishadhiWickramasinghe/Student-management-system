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
public class Students {
    private String studentId;
    private String name;
    private String phoneNumber;
    private String email;
    private String course;
    private String gender;

    public Students(String studentId, String name, String phoneNumber, String email, String course, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.course = course;
        this.gender = gender;
    }

    public Students(String studentId) {
         this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public boolean saveStudent() {
    try {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String sql = "INSERT INTO students (student_id, name, phone_number, email, course, gender) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, studentId);
        ps.setString(2, name);
        ps.setString(3, phoneNumber);
        ps.setString(4, email);
        ps.setString(5, course);
        ps.setString(6, gender);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean updateStudent() {
    try {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String sql = "UPDATE students SET name = ?, phone_number = ?, email = ?, course = ?, gender = ? WHERE student_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, phoneNumber);
        ps.setString(3, email);
        ps.setString(4, course);
        ps.setString(5, gender);
        ps.setString(6, studentId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean deleteStudent() {
    try {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String sql = "DELETE FROM students WHERE student_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, studentId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean searchStudentDetails() {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            String sql = "SELECT name, course FROM students WHERE student_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, studentId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
                course = rs.getString("course");
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static ArrayList<String[]> getAllStudents() {
        ArrayList<String[]> studentsList = new ArrayList<>();

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            String sql = "SELECT student_id, name, phone_number, email, course, gender FROM students";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] row = {
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getString("gender")
                };
                studentsList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentsList;
    }
}
