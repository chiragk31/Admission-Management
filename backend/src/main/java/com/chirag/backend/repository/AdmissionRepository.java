package com.chirag.backend.repository;

import com.chirag.backend.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {

    // Get all admissions of a student
    List<Admission> findByStudentId(Long studentId);
}
