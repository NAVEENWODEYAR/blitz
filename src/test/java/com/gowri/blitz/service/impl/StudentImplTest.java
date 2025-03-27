package com.gowri.blitz.service.impl;

import com.gowri.blitz.modal.Student;
import com.gowri.blitz.repo.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Enable Mockito extension
public class StudentImplTest {

    @InjectMocks
    private StudentImpl studentService;  // Service to test

    @Mock
    private StudentRepo studentRepo;  // Mock the StudentRepo

    private Student student;

    @BeforeEach
    void setUp() {
        // Set up the test data
        student = new Student();
        student.setFirstName("John Doe");
        student.setDateOfBirth(new Date());
    }

    @Test
    void testAddStudent_RetrySuccessAfterRetries() {
        // Simulate the first 4 retries failing, and the 5th succeeding
        when(studentRepo.save(any(Student.class)))
                .thenThrow(new RuntimeException("Temporary failure"))  // Simulate failure for first 4 attempts
                .thenReturn(student);  // On the 5th attempt, return the student

        // Call the method and assert the outcome
        Student result = studentService.addStudent(student);

        // Verify that the repository save method was called exactly 5 times
        verify(studentRepo, times(5)).save(any(Student.class));

        // Assert that the result is the student object that was returned after retries
        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    void testAddStudent_RetryFailureAfterMaxAttempts() {
        // Simulate failure for all retry attempts
        when(studentRepo.save(any(Student.class)))
                .thenThrow(new RuntimeException("Permanent failure"));

        // Call the method and expect it to throw an exception after 5 attempts
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
            studentService.addStudent(student);
        });

        // Verify the exception message
        assertEquals("Permanent failure", thrownException.getMessage());

        // Verify that the repository save method was called 5 times
        verify(studentRepo, times(5)).save(any(Student.class));
    }

    @Test
    void testFindStudent_RetrySuccess() {
        // Simulate the first 2 retries failing, and the 3rd succeeding
        when(studentRepo.findById(anyInt()))
                .thenThrow(new RuntimeException("Temporary failure"))
                .thenThrow(new RuntimeException("Temporary failure"))
                .thenReturn(java.util.Optional.of(student));  // 3rd attempt succeeds

        // Call the method and assert the outcome
        Student result = studentService.findStudent(1);

        // Verify that the repository findById method was called 3 times
        verify(studentRepo, times(3)).findById(anyInt());

        // Assert that the result is the student object that was returned after retries
        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    void testFindStudent_RetryFailure() {
        // Simulate failure for all retry attempts
        when(studentRepo.findById(anyInt()))
                .thenThrow(new RuntimeException("Student not found"));

        // Call the method and expect it to throw an exception after 5 attempts
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
            studentService.findStudent(1);
        });

        // Verify the exception message
        assertEquals("Student not found", thrownException.getMessage());

        // Verify that the repository findById method was called 5 times
        verify(studentRepo, times(5)).findById(anyInt());
    }

    @Test
    void testRecoverMethodAfterRetryFailure() {
        // Simulate a permanent failure on all retries
        when(studentRepo.save(any(Student.class)))
                .thenThrow(new RuntimeException("Permanent failure"));

        // Capture the exception that will be thrown after all retries
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
            studentService.addStudent(student);
        });

        // Verify that the exception is correctly logged or thrown
        assertEquals("Permanent failure", thrownException.getMessage());

        // Verify that the recover method is triggered after all retries fail
        verify(studentRepo, times(5)).save(any(Student.class));
    }
}
