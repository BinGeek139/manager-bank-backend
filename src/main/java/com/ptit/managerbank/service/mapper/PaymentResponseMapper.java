package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.request.PaymentRequestDTO;
import com.ptit.managerbank.dto.response.PaymentResponseDTO;
import com.ptit.managerbank.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentResponseMapper extends EntityMapper<PaymentResponseDTO, Payment>  {
}
