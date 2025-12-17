package com.chirag.backend.service;

import com.chirag.backend.dto.PaymentRequestDTO;
import com.chirag.backend.entity.Admission;
import com.chirag.backend.entity.Payment;
import com.chirag.backend.entity.Receipt;
import com.chirag.backend.repository.AdmissionRepository;
import com.chirag.backend.repository.PaymentRepository;
import com.chirag.backend.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepo;
    private final AdmissionRepository admissionRepo;
    private final ReceiptRepository receiptRepo;

    public PaymentService(
            PaymentRepository paymentRepo,
            AdmissionRepository admissionRepo,
            ReceiptRepository receiptRepo) {

        this.paymentRepo = paymentRepo;
        this.admissionRepo = admissionRepo;
        this.receiptRepo = receiptRepo;
    }

    // ===== CREATE PAYMENT =====
    public Receipt makePayment(PaymentRequestDTO dto) {

        Admission admission = admissionRepo.findById(dto.getAdmissionId())
                .orElseThrow(() -> new RuntimeException("Admission not found"));

        // Prevent over-payment
        if (dto.getAmountPaid() > admission.getFeesRemaining()) {
            throw new RuntimeException("Payment exceeds remaining fees");
        }

        // Save payment
        Payment payment = new Payment();
        payment.setAdmission(admission);
        payment.setAmountPaid(dto.getAmountPaid());
        payment.setPaymentMode(dto.getPaymentMode());
        payment.setRemarks(dto.getRemarks());
        paymentRepo.save(payment);

        // Update admission fees
        double updatedPaid = admission.getFeesPaid() + dto.getAmountPaid();
        admission.setFeesPaid(updatedPaid);
        admission.setFeesRemaining(admission.getTotalFees() - updatedPaid);
        admissionRepo.save(admission);

        // Generate receipt
        Receipt receipt = new Receipt();
        receipt.setPayment(payment);
        receipt.setReceiptNumber("REC-" + System.currentTimeMillis());

        return receiptRepo.save(receipt);
    }

    // ===== READ PAYMENTS =====
    public List<Payment> getPaymentsByAdmission(Long admissionId) {
        return paymentRepo.findByAdmissionId(admissionId);
    }

    public List<Receipt> getReceiptsByAdmission(Long admissionId) {
        return receiptRepo.findAll()
                .stream()
                .filter(r -> r.getPayment().getAdmission().getId().equals(admissionId))
                .toList();
    }
}
