/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentmanagement;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Student {
    private String name;
    private String id;
    private int age;
    private String email;
    private String course;

    public Student(String name, String id, int age, String email, String course) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    // Method to capture a new student
    public static void SaveStudent(ArrayList<Student> students) {
        String id;
        while (true) {
            id = JOptionPane.showInputDialog("Enter student ID:");
            if (isDuplicateId(students, id)) {
                JOptionPane.showMessageDialog(null, "Student with this ID already exists. Please enter a unique ID.");
            } else {
                break;
            }
        }

        String name = JOptionPane.showInputDialog("Enter student name:");
        String email = JOptionPane.showInputDialog("Enter student email:");
        String course = JOptionPane.showInputDialog("Enter student course:");

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
                JOptionPane.showMessageDialog(null, "You have entered an incorrect student age! Please re-enter.");
            }
        }

        // Create a new student and add to the list
        Student student = new Student(name, id, age, email, course);
        students.add(student);

        JOptionPane.showMessageDialog(null, "Student details have been successfully saved.");
    }

    // Method to search for a student by ID
    public static void SearchStudent(ArrayList<Student> students) {
        String id = JOptionPane.showInputDialog("Enter the student ID to search:");

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

    // Method to delete a student by ID
    public static void DeleteStudent(ArrayList<Student> students) {
        String id = JOptionPane.showInputDialog("Enter the student ID to delete:");

        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                // Confirm deletion
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete student " + id + " from the system?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    students.remove(student);
                    JOptionPane.showMessageDialog(null, "Student with Student Id: " + id + " was deleted!");
                } else {
                    JOptionPane.showMessageDialog(null, "Deletion canceled.");
                }
                return;
            }
        }

        // If no student is found
        JOptionPane.showMessageDialog(null, "Student with Student Id: " + id + " was not found!");
    }
    // Method to generate and display a student report
    public static void StudentReport(ArrayList<Student> students) {
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
    // Method to exit the application
    public static void ExitStudentApplication() {
        JOptionPane.showMessageDialog(null, "Exiting application...");
        System.exit(0);
    }
    // Helper method to check for duplicate IDs
    private static boolean isDuplicateId(ArrayList<Student> students, String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}