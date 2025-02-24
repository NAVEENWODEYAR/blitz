package com.gowri.blitz.repo;

/*
 * @author NaveenWodeyar
 * @date 24-02-2025
 */

import com.gowri.blitz.modal.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
