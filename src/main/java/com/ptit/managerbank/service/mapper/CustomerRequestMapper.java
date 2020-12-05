package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.request.CustomerRequestDTO;
import com.ptit.managerbank.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper extends EntityMapper<CustomerRequestDTO, Customer> {
}
