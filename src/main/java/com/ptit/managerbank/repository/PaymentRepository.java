package com.ptit.managerbank.repository;

import com.ptit.managerbank.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository  extends JpaRepository<Payment,Integer> {
}
