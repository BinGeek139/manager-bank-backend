package com.ptit.managerbank.service;

import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public  interface  CustomerService {
        CustomerDTO findCustomerById(Integer id);
        CustomerDTO saveCustomer(CustomerDTO CustomerDTO);
        CustomerDTO updateCustomer(CustomerDTO CustomerDTO);
        void deleteCustomer(Integer id);
        Page<CustomerDTO> getCustomerByName(String name, Pageable pageable);
        ResponseData validateCustomer(CustomerDTO CustomerDTO);


}
