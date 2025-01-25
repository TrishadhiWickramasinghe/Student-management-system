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
import model.Students;

/**
 *
 * @author DELL
 */
public class StudentController {
    public void addStudent(String studentId, String name, String phoneNumber, String email, String course, String gender) {
    try {
        Students student = new Students(studentId, name, phoneNumber, email, course, gender);
        boolean isSaved = student.saveStudent();

        if (isSaved) {
            JOptionPane.showMessageDialog(null, "Student added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add student. Please try again.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}
    
    public void updateStudent(String studentId, String name, String phoneNumber, String email, String course, String gender) {
    try {
        Students student = new Students(studentId, name, phoneNumber, email, course, gender);
        boolean isUpdated = student.updateStudent();

        if (isUpdated) {
            JOptionPane.showMessageDialog(null, "Student updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update student. Please try again.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    public void deleteStudent(String studentId) {
    try {
        Students student = new Students(studentId);
        boolean isDeleted = student.deleteStudent();

        if (isDeleted) {
            JOptionPane.showMessageDialog(null, "Student deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete student. Please try again.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    public void searchByStudentId(String studentId, JTextField txtName, JTextField txtCourse) {
        Students search = new Students(studentId);
        boolean found = search.searchStudentDetails();

        if (found) {
            // Populate the text fields with the retrieved data
            txtName.setText(search.getName());
            txtCourse.setText(search.getCourse());
        } else {
            JOptionPane.showMessageDialog(null, "Student not found with ID: " + studentId);
            // Optionally clear the text fields if student not found
            txtName.setText("");
            txtCourse.setText("");
        }
    }
    
    public void loadAllStudents(JTable table) {
        ArrayList<String[]> studentsList = Students.getAllStudents();

        // Clear the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Add rows to the table
        for (String[] row : studentsList) {
            model.addRow(row);
        }
    }
}
