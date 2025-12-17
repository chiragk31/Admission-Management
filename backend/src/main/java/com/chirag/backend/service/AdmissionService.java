package com.chirag.backend.service;

import com.chirag.backend.dto.AdmissionRequestDTO;
import com.chirag.backend.entity.Admission;
import com.chirag.backend.entity.Course;
import com.chirag.backend.entity.Student;
import com.chirag.backend.repository.AdmissionRepository;
import com.chirag.backend.repository.CourseRepository;
import com.chirag.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdmissionService {

    private final AdmissionRepository admissionRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public AdmissionService(
            AdmissionRepository admissionRepo,
            StudentRepository studentRepo,
            CourseRepository courseRepo) {

        this.admissionRepo = admissionRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    // ===== CREATE =====
    public Admission admitStudent(AdmissionRequestDTO dto) {

        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Admission admission = new Admission();
        admission.setStudent(student);
        admission.setCourse(course);
        admission.setAdmissionDate(LocalDate.now());
        admission.setTotalFees(course.getTotalFees());
        admission.setFeesPaid(0.0);
        admission.setFeesRemaining(course.getTotalFees());
        admission.setStatus("ACTIVE");

        return admissionRepo.save(admission);
    }

    // ===== READ =====
    public List<Admission> getAllAdmissions() {
        return admissionRepo.findAll();
    }

    public Admission getAdmissionById(Long id) {
        return admissionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));
    }

    public List<Admission> getAdmissionsByStudent(Long studentId) {
        return admissionRepo.findByStudentId(studentId);
    }
}
