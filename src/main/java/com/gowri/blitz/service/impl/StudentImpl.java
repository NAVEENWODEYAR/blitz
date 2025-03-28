package com.gowri.blitz.service.impl;

import com.gowri.blitz.exceptions.BlitzException;
import com.gowri.blitz.modal.Student;
import com.gowri.blitz.repo.StudentRepo;
import com.gowri.blitz.service.StudentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
 * @author NaveenWodeyar
 * @date 24-02-2025
 */
@Component
public class StudentImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentImpl.class);

    @Autowired
    private StudentRepo studentRepo;

    @Override
    @Transactional(rollbackOn = Exception.class)
    @Retryable(value = {Exception.class}, maxAttempts = 5, backoff = @org.springframework.retry.annotation.Backoff(delay = 2000, multiplier = 2))
    public Student addStudent(Student student) {
        try {
            log.info("Inserting into Student: {}", student);
            return studentRepo.save(student);
        } catch (Exception e) {
            log.error("Error while inserting student: {}. Reason: {}", student, e.getMessage());
            throw e;  // Rethrow exception to trigger retry
        }
    }

    @Override
    @Transactional
    @Retryable(value = {Exception.class}, maxAttempts = 5, backoff = @org.springframework.retry.annotation.Backoff(delay = 2000, multiplier = 2))
    public Student findStudent(Integer stId) {
        try {
            log.info("Fetching student by ID: {}", stId);
            Optional<Student> student = studentRepo.findById(stId);
            return student.orElseThrow(() -> new RuntimeException("Student not found with ID: " + stId));
        } catch (Exception e) {
            log.error("Error fetching student by ID: {}. Reason: {}", stId, e.getMessage());
            throw e;  // Rethrow exception to trigger retry
        }
    }

    @Override
    @Retryable(value = {Exception.class}, maxAttempts = 5, backoff = @org.springframework.retry.annotation.Backoff(delay = 2000, multiplier = 2))
    public Student editStudent(Integer stId) {
        try {
            log.debug("Modifying student with ID: {}", stId);
            // Add logic for modifying a student here if needed
            return null; // Placeholder for actual logic
        } catch (Exception e) {
            log.error("Error editing student with ID: {}. Reason: {}", stId, e.getMessage());
            throw e;
        }
    }

    @Override
    @Retryable(value = {Exception.class}, maxAttempts = 5, backoff = @org.springframework.retry.annotation.Backoff(delay = 2000, multiplier = 2))
    public void deleteStudent(Integer stId) {
        try {
            log.warn("Removing student with ID: {}", stId);
            studentRepo.deleteById(stId);
        } catch (Exception e) {
            log.error("Error while deleting student with ID: {}. Reason: {}", stId, e.getMessage());
            throw e;  // Rethrow exception to trigger retry
        }
    }

    // Recovery method that will be invoked after 5 failed retry attempts
    @Recover
    public void recover(Exception e, Integer stId) throws BlitzException {
        // Log the failure after retries are exhausted
        log.error("Failed after 5 retry attempts to process operation for student with ID: {}. Final exception: {}", stId, e.getMessage());

        // Throw a BlitzException with a custom message, the original exception as the cause, and additional context
        throw new BlitzException("Unable to process the request after multiple retry attempts", e, 500, "Retry attempts exhausted for student ID: " + stId);
    }
}
