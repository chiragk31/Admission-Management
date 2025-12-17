package com.chirag.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "admissions")
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @Column(name = "total_fees")
    private Double totalFees;

    @Column(name = "fees_paid")
    private Double feesPaid = 0.0;

    @Column(name = "fees_remaining")
    private Double feesRemaining;

    @Column(name = "status")
    private String status = "ACTIVE"; // ACTIVE / CANCELLED

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Double getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(Double totalFees) {
        this.totalFees = totalFees;
    }

    public Double getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(Double feesPaid) {
        this.feesPaid = feesPaid;
    }

    public Double getFeesRemaining() {
        return feesRemaining;
    }

    public void setFeesRemaining(Double feesRemaining) {
        this.feesRemaining = feesRemaining;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
