package com.chirag.backend.controller;

import com.chirag.backend.dto.PaymentRequestDTO;
import com.chirag.backend.entity.Payment;
import com.chirag.backend.entity.Receipt;
import com.chirag.backend.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:8081")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // ===== CREATE PAYMENT =====
    @PostMapping
    public Receipt makePayment(@RequestBody PaymentRequestDTO dto) {
        return service.makePayment(dto);
    }

    // ===== READ PAYMENTS =====
    @GetMapping("/admission/{admissionId}")
    public List<Payment> getPaymentsByAdmission(@PathVariable Long admissionId) {
        return service.getPaymentsByAdmission(admissionId);
    }

    // ===== READ RECEIPTS =====
    @GetMapping("/receipts/admission/{admissionId}")
    public List<Receipt> getReceiptsByAdmission(@PathVariable Long admissionId) {
        return service.getReceiptsByAdmission(admissionId);
    }
}
