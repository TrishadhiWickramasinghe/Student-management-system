/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentmanagementsystem;

import javax.swing.SwingUtilities;
import view.Add_Course_Page;
import view.Add_Marks_Page;
import view.Check_Result_Page;
import view.Dashboard;
import view.Manage_Course_Page;
import view.Login_Page;
import view.Report_Page;
import view.Student_Page;

/**
 *
 * @author user
 */
public class StudentManagementSystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                
                new Login_Page().setVisible(true);
            }
        });
    }
}
