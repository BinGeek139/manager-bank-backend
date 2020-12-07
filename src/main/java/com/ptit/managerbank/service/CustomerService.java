package com.ptit.managerbank.service;

import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.CustomerDTO;
import com.ptit.managerbank.dto.request.CustomerRequestDTO;
import com.ptit.managerbank.dto.response.CustomerResponseDTO;
import com.ptit.managerbank.dto.response.CustomerStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public  interface  CustomerService {
        CustomerDTO findCustomerById(Integer id);
        CustomerResponseDTO saveCustomer(CustomerRequestDTO CustomerDTO);
        CustomerDTO updateCustomer(CustomerDTO CustomerDTO);
        void deleteCustomer(Integer id);
        Page<CustomerDTO> getCustomerByName(String name, Pageable pageable);
//        ResponseData validateCustomer(CustomerDTO CustomerDTO);
        boolean checkCodeExisted(String code);
        List<CustomerStatistic>  statisticCustomerMaxSaving(Integer number);



}
