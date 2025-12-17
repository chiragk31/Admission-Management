package com.chirag.backend.controller;

import com.chirag.backend.entity.Course;
import com.chirag.backend.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:8081")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    public Course add(@RequestBody Course c) {
        return service.addCourse(c);
    }

    @GetMapping
    public List<Course> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return service.getCourseById(id);
    }
}
