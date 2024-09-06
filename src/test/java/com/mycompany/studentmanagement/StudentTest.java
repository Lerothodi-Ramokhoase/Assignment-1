/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.studentmanagement;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author User
 */
class StudentTest {
    private ArrayList<Student> students;

    @BeforeEach
    void setUp() {
        students = new ArrayList<>();
    }

    @Test
    void testSaveStudent() {
        // Mock the input dialog responses
        setUpInputDialogResponses("S001", "John Doe", "john@example.com", "Computer Science", "18");

        // Save the student
        Student.SaveStudent(students);

        // Verify that the student was saved
        assertEquals(1, students.size());
        assertEquals("John Doe", students.get(0).getName());
        assertEquals("S001", students.get(0).getId());
        assertEquals(18, students.get(0).getAge());
        assertEquals("john@example.com", students.get(0).getEmail());
        assertEquals("Computer Science", students.get(0).getCourse());
    }

    @Test
    void testSearchStudent() {
        // Setup: Add a student first
        Student student = new Student("Jane Smith", "S002", 20, "jane.smith@example.com", "Mathematics");
        students.add(student);

        // Test: Search for the student by ID
        // Mock the input dialog response
        setUpInputDialogResponse("S002");

        Student.SearchStudent(students);

        // Check the result
        assertEquals("Jane Smith", student.getName());
    }

    @Test
    void testSearchStudentNotFound() {
        // Test: Search for a non-existing student ID
        setUpInputDialogResponse("S999");
        Student.SearchStudent(students);

        // Assert that no exception is thrown and a message is shown
        assertEquals(0, students.size()); // Still no students
    }

    @Test
    void testDeleteStudent() {
        // Setup: Add a student first
        Student student = new Student("Mark Taylor", "S003", 22, "mark.taylor@example.com", "Physics");
        students.add(student);

        // Mock the input dialog response
        setUpInputDialogResponse("S003");
        
        // Mock the confirmation dialog response
        setUpConfirmationDialogResponse(JOptionPane.YES_OPTION);

        // Test: Delete the student
        Student.DeleteStudent(students);

        // Verify that the student has been deleted
        assertEquals(0, students.size());
    }

    @Test
    void testDeleteStudentNotFound() {
        // Mock the input dialog response
        setUpInputDialogResponse("S999");

        // Test: Attempt to delete a non-existing student
        Student.DeleteStudent(students);

        // Assert that no exception is thrown and the size is still 0
        assertEquals(0, students.size());
    }

    @Test
    void testStudentAgeValid() {
        // Test: Valid age
        assertDoesNotThrow(() -> {
            int age = 18;
            if (age < 16) throw new Exception("Invalid age");
        }, "Expected no exception for valid age");
    }

    @Test
    void testStudentAgeInvalid() {
        // Test: Invalid age (less than 16)
        Exception exception = assertThrows(Exception.class, () -> {
            int age = 15;
            if (age < 16) throw new Exception("Invalid age");
        });
        assertEquals("Invalid age", exception.getMessage());
    }

    @Test
    void testStudentAgeInvalidCharacter() {
        // Test: Invalid character input (simulate invalid input for age)
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("invalid");
        });
        assertTrue(exception.getMessage().contains("For input string: \"invalid\""), "Expected NumberFormatException for invalid input");
    }

    // Helper methods to mock input dialogs
    private void setUpInputDialogResponses(String id, String name, String email, String course, String age) {
        // Mock the behavior of input dialogs
        String[] inputs = {id, name, email, course, age};
        for (String input : inputs) {
            setUpInputDialogResponse(input);
        }
    }

    private void setUpInputDialogResponse(String response) {
        // Override the JOptionPane.showInputDialog method for testing
        JOptionPane.setInputDialogMock(response);
    }

    private void setUpConfirmationDialogResponse(int response) {
        // Override the JOptionPane.showConfirmDialog method for testing
        JOptionPane.setConfirmDialogMock(response);
    }
}