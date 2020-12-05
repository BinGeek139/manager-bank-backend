package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.request.PaymentRequestDTO;
import com.ptit.managerbank.dto.response.CustomerResponseDTO;
import com.ptit.managerbank.model.Customer;
import com.ptit.managerbank.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentRequestMapper  extends EntityMapper<PaymentRequestDTO, Payment> {
}
