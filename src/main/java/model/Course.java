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
public class Course {
    private String subjectId;
    private String semester;
    private String course;

    public Course(String subjectId, String semester, String course) {
        this.subjectId = subjectId;
        this.semester = semester;
        this.course = course;
    }

    public Course() {
    }

   public static Course forSubjectId(String subjectId) {
    Course course = new Course();
    course.subjectId = subjectId;
    return course;
}

   public static Course forCourseName(String courseName) {
    Course course = new Course();
    course.course = courseName;
    return course;
}

    

    public String getSubjectId() {
        return subjectId;
    }
    
    
 

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
    // Method to save the course
    public boolean saveCourse() {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            String sql = "INSERT INTO courses (subject_id, semester, course) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subjectId);
            ps.setString(2, semester);
            ps.setString(3, course);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateCourse() {
    try {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String sql = "UPDATE courses SET semester = ?, course = ? WHERE subject_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, semester);
        ps.setString(2, course);
        ps.setString(3, subjectId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean deleteCourse() {
    try {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String sql = "DELETE FROM courses WHERE subject_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, subjectId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean deleteCoursesByName() {
    try {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String sql = "DELETE FROM courses WHERE course = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, course);

        
        int rowsAffected = ps.executeUpdate();
       

        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

}

    public boolean searchSemester() {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            String sql = "SELECT semester FROM courses WHERE subject_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subjectId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                semester = rs.getString("semester");
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static ArrayList<String[]> getAllCourses() {
        ArrayList<String[]> coursesList = new ArrayList<>();

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            String sql = "SELECT subject_id, course, semester FROM courses";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] row = {
                    rs.getString("subject_id"),
                    rs.getString("course"),
                    rs.getString("semester")
                };
                coursesList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return coursesList;
    }
}
