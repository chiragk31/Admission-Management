package com.chirag.backend.repository;

import com.chirag.backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

// CourseRepository
public interface CourseRepository extends JpaRepository<Course, Long> {}
