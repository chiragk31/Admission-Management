package com.chirag.backend.repository;

import com.chirag.backend.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

// ReceiptRepository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {}
