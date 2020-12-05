package com.ptit.managerbank.service;

import com.ptit.managerbank.dto.request.PaymentRequestDTO;
import com.ptit.managerbank.dto.response.PaymentResponseDTO;
import com.ptit.managerbank.model.Payment;

import java.util.List;

public interface PaymentService {
    PaymentResponseDTO addPayment(PaymentRequestDTO paymentRequestDTO);
    List<PaymentResponseDTO> findHistoryPayment(String account);
}
