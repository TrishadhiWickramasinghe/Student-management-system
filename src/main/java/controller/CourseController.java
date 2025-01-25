/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Course;

/**
 *
 * @author DELL
 */
public class CourseController {
    public void addCourse(String subjectId, String semester, String course) {
    try {
        Course courseObj = new Course(subjectId, semester, course);
        boolean isSaved = courseObj.saveCourse();

        if (isSaved) {
            JOptionPane.showMessageDialog(null, "Course added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add course. Please try again.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    public void updateCourse(String subjectId, String semester, String course) {
    try {
        Course courseObj = new Course(subjectId, semester, course);
        boolean isUpdated = courseObj.updateCourse();

        if (isUpdated) {
            JOptionPane.showMessageDialog(null, "Course updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update course. Please try again.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    public void deleteCourse(String subjectId) {
    try {
        Course courseObj = Course.forSubjectId(subjectId);
        boolean isDeleted = courseObj.deleteCourse();

        if (isDeleted) {
            JOptionPane.showMessageDialog(null, "Course deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete course. Please try again.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    public void deleteCoursesByName(String courseName) {
    try {
        Course courseObj = Course.forCourseName(courseName);
        boolean isDeleted = courseObj.deleteCoursesByName();

        if (isDeleted) {
            JOptionPane.showMessageDialog(null, "Courses deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete courses. Please try again.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    public void searchBySubjectId(String subjectId, JTextField txtSemester) {
        Course search = Course.forSubjectId(subjectId);
        boolean found = search.searchSemester();

        if (found) {
            // Populate the text field with the retrieved semester
            txtSemester.setText(search.getSemester());
        } else {
            JOptionPane.showMessageDialog(null, "No semester found for Subject ID: " + subjectId);
            // Optionally clear the text field if subject not found
            txtSemester.setText("");
        }
    }
    
    public void loadAllCourses(JTable table) {
        ArrayList<String[]> coursesList = Course.getAllCourses();

        // Clear the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Add rows to the table
        for (String[] row : coursesList) {
            model.addRow(row);
        }
    }
}
