package com.gowri.blitz.service.impl;

import com.gowri.blitz.modal.Student;
import com.gowri.blitz.repo.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentImplTest {

    @InjectMocks
    private StudentImpl studentService;

    @Mock
    private StudentRepo studentRepo;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setFirstName("John Doe");
        student.setDateOfBirth(new Date());
    }

    @Test
    void testAddStudentWithRetry() {
        // Simulate failure for first 4 attempts, success on 5th attempt
        when(studentRepo.save(any(Student.class)))
                .thenThrow(new RuntimeException("Temporary failure")) // Failure for 1st attempt
                .thenThrow(new RuntimeException("Temporary failure")) // Failure for 2nd attempt
                .thenThrow(new RuntimeException("Temporary failure")) // Failure for 3rd attempt
                .thenThrow(new RuntimeException("Temporary failure")) // Failure for 4th attempt
                .thenReturn(student); // Success on 5th attempt

        // Call the method
        Student result = studentService.addStudent(student);

        // Verify that the repository save method was called 5 times
        verify(studentRepo, times(5)).save(any(Student.class));

        // Assert that the result is the student object returned after retries
        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    void testFindStudentWithRetry() {
        // Simulate failure for first 4 attempts, success on 5th attempt
        when(studentRepo.findById(anyInt()))
                .thenThrow(new RuntimeException("Temporary failure")) // Failure for 1st attempt
                .thenThrow(new RuntimeException("Temporary failure")) // Failure for 2nd attempt
                .thenThrow(new RuntimeException("Temporary failure")) // Failure for 3rd attempt
                .thenThrow(new RuntimeException("Temporary failure")) // Failure for 4th attempt
                .thenReturn(Optional.of(student)); // Success on 5th attempt

        // Call the method
        Student result = studentService.findStudent(1);

        // Verify that the repository findById method was called 5 times
        verify(studentRepo, times(5)).findById(anyInt());

        // Assert that the result is the student object returned after retries
        assertNotNull(result);
        assertEquals(student, result);
    }
}
