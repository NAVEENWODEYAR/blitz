package com.gowri.blitz.service;

/*
 * @author NaveenWodeyar
 * @date 24-02-2025
 */

import com.gowri.blitz.modal.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    Student addStudent(Student student);
    Student findStudent(Integer stId);
    Student editStudent(Integer stId);
    void deleteStudent(Integer stId);
}
