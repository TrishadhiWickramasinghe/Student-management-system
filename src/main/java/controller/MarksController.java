/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Marks;

/**
 *
 * @author DELL
 */
public class MarksController {
    public void addMarks(String studentId, String semester, String subjectId, int marks) {
        try {
            Marks marksObj = new Marks(studentId, semester, subjectId, marks);
            boolean isSaved = marksObj.saveMarks();

            if (isSaved) {
                JOptionPane.showMessageDialog(null, "Marks added successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add marks. Please try again.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void updateStudentMarks(String studentId,String semester, String subjectId,  int marks) {
        Marks marksObj = new Marks(studentId,semester, subjectId,  marks);
        boolean isUpdated = marksObj.updateMarks();

        if (isUpdated) {
            JOptionPane.showMessageDialog(null, "Marks updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update marks. Please check the inputs and try again.");
        }
    }
    
    public void loadMarksByStudentId(String studentId, JTable table) {
        ArrayList<String[]> marksList = Marks.getMarksByStudentId(studentId);

        // Clear the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Add rows to the table
        for (String[] row : marksList) {
            model.addRow(row);
        }
    }
}
