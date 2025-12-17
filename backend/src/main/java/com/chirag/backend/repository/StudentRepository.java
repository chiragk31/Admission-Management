package com.chirag.backend.repository;

import com.chirag.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// StudentRepository
public interface StudentRepository extends JpaRepository<Student, Long> {}


