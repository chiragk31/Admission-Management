package com.chirag.backend.controller;

import com.chirag.backend.dto.StudentRequestDTO;
import com.chirag.backend.entity.Student;
import com.chirag.backend.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:8081")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student add(@RequestBody StudentRequestDTO dto) {
        return service.addStudent(dto);
    }

    @GetMapping
    public List<Student> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
