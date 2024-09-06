/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentmanagement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class StudentManagement {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        showMainMenu();
    }

    private static void showMainMenu() {
        while (true) {
            String mainMenu = "Student Manager application\n"
                    + "*******************************\n"
                    + "Enter (1) to launch menu or any other key to exit";
            String choice = JOptionPane.showInputDialog(mainMenu);
            if (!"1".equals(choice)) {
                break;
            }
            
            String menu = "Please select one of the following menu items:\n"
                    + "1. Capture a new student\n"
                    + "2. Search for a student\n"
                    + "3. Delete a student\n"
                    + "4. Print student report\n"
                    + "5. Exit application";

            choice = JOptionPane.showInputDialog(menu);

            switch (choice) {
                case "1":
                    captureNewStudent();
                    break;
                case "2":
                    searchStudent();
                    break;
                case "3":
                    deleteStudent();
                    break;
                case "4":
                    printStudentReport();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Exiting application...");
                    return; // Exit the application
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

    private static void captureNewStudent() {
        String name = JOptionPane.showInputDialog("Enter student name:");
        String id = JOptionPane.showInputDialog("Enter student ID:");
        String email = JOptionPane.showInputDialog("Enter student email:");
        String course = JOptionPane.showInputDialog("Enter student course:");
        
        // Input validation for student age
        int age = 0;
        while (true) {
            String ageInput = JOptionPane.showInputDialog("Enter student age:");
            try {
                age = Integer.parseInt(ageInput);
                if (age < 16) {
                    throw new Exception("Invalid age");
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "You have entered an incorrect student age!!!\nPlease re-enter the student age >>");
            }
        }

        // Create a new student
        Student student = new Student(name, id, age, email, course);
        students.add(student);

        // Inform the user that the student details have been successfully saved
        JOptionPane.showMessageDialog(null, "Student details have been successfully saved.");
    }

    private static void searchStudent() {
        String id = JOptionPane.showInputDialog("Enter the student id to search:");

        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                // Student found, display details
                JOptionPane.showMessageDialog(null, "STUDENT ID: " + student.getId() +
                        "\nSTUDENT NAME: " + student.getName() +
                        "\nSTUDENT AGE: " + student.getAge() +
                        "\nSTUDENT EMAIL: " + student.getEmail() +
                        "\nSTUDENT COURSE: " + student.getCourse());
                return;
            }
        }

        // If no student is found
        JOptionPane.showMessageDialog(null, "Student with Student Id: " + id + " was not found!");
    }

    private static void deleteStudent() {
        String id = JOptionPane.showInputDialog("Enter the student id to delete:");

        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                // Confirm deletion
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete student " + id + " from the system?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    students.remove(student);
                    JOptionPane.showMessageDialog(null, "Student with Student Id: " + id + " WAS deleted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Deletion canceled.");
                }
                return;
            }
        }

        // If no student is found
        JOptionPane.showMessageDialog(null, "Student with Student Id: " + id + " was not found!");
    }

    private static void printStudentReport() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No students to display.");
        } else {
            StringBuilder report = new StringBuilder();
            int count = 1;
            for (Student student : students) {
                report.append("STUDENT ").append(count++).append("\n")
                        .append("STUDENT ID: ").append(student.getId()).append("\n")
                        .append("STUDENT NAME: ").append(student.getName()).append("\n")
                        .append("STUDENT AGE: ").append(student.getAge()).append("\n")
                        .append("STUDENT EMAIL: ").append(student.getEmail()).append("\n")
                        .append("STUDENT COURSE: ").append(student.getCourse()).append("\n")
                        .append("-------------------------\n");
            }
            JOptionPane.showMessageDialog(null, report.toString());
        }
    }
}