package com.gowri.blitz.service.impl;

/*
 * @author NaveenWodeyar
 * @date 24-02-2025
 */

import com.gowri.blitz.modal.Student;
import com.gowri.blitz.repo.StudentRepo;
import com.gowri.blitz.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentImpl.class);

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student addStudent(Student student) {
        log.info("Insert into Student:{} ",student);
        return studentRepo.save(student);
    }

    @Override
    public Student findStudent(Integer stId) {
        Student student = studentRepo.findById(stId).get();
        if (student !=null) {
            return student;
        }
        throw new RuntimeException("Student not found");
    }

    @Override
    public void deleteStudent(Integer stId) {
    log.warn("Removing from student:{} ",stId);
    studentRepo.deleteById(stId);
    }
}
