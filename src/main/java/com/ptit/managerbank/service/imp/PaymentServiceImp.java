package com.ptit.managerbank.service.imp;

import com.ptit.managerbank.dto.request.PaymentRequestDTO;
import com.ptit.managerbank.dto.response.PaymentResponseDTO;
import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.Payment;
import com.ptit.managerbank.service.PaymentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PaymentServiceImp  implements PaymentService {
    @Transactional
    @Override
    public PaymentResponseDTO addPayment(PaymentRequestDTO paymentRequestDTO) {
//        AccountBank accountBank=paymentRequestDTO.getAccountBankSend();
//        AccountBank accountBankReceive=
        return null;
    }

    @Override
    public List<PaymentResponseDTO> findHistoryPayment(String account) {
        return null;
    }

}
