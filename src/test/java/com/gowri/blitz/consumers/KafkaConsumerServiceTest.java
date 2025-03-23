package com.gowri.blitz.consumers;

import com.google.gson.Gson;
import com.gowri.blitz.modal.Student;
import com.gowri.blitz.service.impl.StudentImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;

/*
 * @author NaveenWodeyar
 * @date 23-03-2025
 */

@ExtendWith(MockitoExtension.class)
public class KafkaConsumerServiceTest {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerServiceTest.class);

    @InjectMocks
    private KafkaConsumerService kafkaConsumerService;

    @Mock
    private StudentImpl studentImpl;

    @Mock
    private Logger logger;

    @BeforeEach
    public void setUp() {
        // You can initialize any necessary components here if needed
    }

    @Test
    public void testListen_shouldConsumeMessageAndSaveStudent() {
        // Arrange
        String message = "{\"id\": 1, \"name\": \"John Doe\"}";
        Student student = new Gson().fromJson(message, Student.class);

        // Act
        kafkaConsumerService.listen(message);

        // Assert
        verify(studentImpl, times(1)).addStudent(student); // Verify that addStudent is called once
        verify(logger, times(1)).info("Received message: {}", message); // Verify that the info log is printed
        verify(logger, times(1)).debug("Student :{}", student); // Verify that the debug log for student is printed
    }

    @Test
    public void testListen_shouldHandleDeserializationException() {
        // Arrange
        String invalidMessage = "{ invalid json }";

        // Act
        kafkaConsumerService.listen(invalidMessage);

        // Assert
        verify(logger, times(1)).error("Error deserializing message: {}", invalidMessage, any(Exception.class)); // Verify error log for deserialization
    }

    @Test
    public void testListen_shouldHandleExceptionInAddStudent() {
        // Arrange
        String message = "{\"id\": 1, \"name\": \"John Doe\"}";
        Student student = new Gson().fromJson(message, Student.class);

        // Simulate an exception in addStudent
        doThrow(new RuntimeException("Database Error")).when(studentImpl).addStudent(student);

        // Act
        kafkaConsumerService.listen(message);

        // Assert
        verify(studentImpl, times(1)).addStudent(student); // Verify addStudent was called
        verify(logger, times(1)).error("Exception occured while saving student:{}", "Database Error"); // Verify that the exception is logged
    }
}
