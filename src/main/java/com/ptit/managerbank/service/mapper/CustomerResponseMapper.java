package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.response.CustomerResponseDTO;
import com.ptit.managerbank.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper extends EntityMapper<CustomerResponseDTO, Customer> {
}