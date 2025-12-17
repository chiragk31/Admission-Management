package com.chirag.backend.controller;

import com.chirag.backend.dto.AdmissionRequestDTO;
import com.chirag.backend.entity.Admission;
import com.chirag.backend.service.AdmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admissions")
@CrossOrigin(origins = "http://localhost:8081")
public class AdmissionController {

    private final AdmissionService service;

    public AdmissionController(AdmissionService service) {
        this.service = service;
    }

    // ===== CREATE =====
    @PostMapping
    public Admission admitStudent(@RequestBody AdmissionRequestDTO dto) {
        return service.admitStudent(dto);
    }

    // ===== READ =====
    @GetMapping
    public List<Admission> getAllAdmissions() {
        return service.getAllAdmissions();
    }

    @GetMapping("/{id}")
    public Admission getAdmissionById(@PathVariable Long id) {
        return service.getAdmissionById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<Admission> getAdmissionsByStudent(@PathVariable Long studentId) {
        return service.getAdmissionsByStudent(studentId);
    }
}
