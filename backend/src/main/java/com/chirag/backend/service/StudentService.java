package com.chirag.backend.service;

import com.chirag.backend.dto.StudentRequestDTO;
import com.chirag.backend.entity.Student;
import com.chirag.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student addStudent(StudentRequestDTO dto) {
        Student s = new Student();
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setEmail(dto.getEmail());
        s.setPhone(dto.getPhone());
        s.setGender(dto.getGender());
        s.setDateOfBirth(dto.getDateOfBirth());
        s.setAddress(dto.getAddress());
        return repo.save(s);
    }

    public List<Student> getAll() {
        return repo.findAll();
    }

    public Student getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}
