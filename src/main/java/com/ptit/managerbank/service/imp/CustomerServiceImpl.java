package com.ptit.managerbank.service.imp;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.Constants;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.CustomerDTO;
import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.Position;
import com.ptit.managerbank.model.Customer;
import com.ptit.managerbank.repository.AccountBankRepository;
import com.ptit.managerbank.repository.PositionRepository;
import com.ptit.managerbank.repository.CustomerRepository;
import com.ptit.managerbank.service.CustomerService;
import com.ptit.managerbank.service.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CustomerServiceImpl  extends BaseComponent implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerRepository customerRepository;


    @Override
    public CustomerDTO findCustomerById(Integer id) {
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            return customerMapper.toDto(optionalCustomer.get());
        }
        return null;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer=customerMapper.toEntity(customerDTO);
        customer=customerRepository.save(customer);
        return  customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer=customerMapper.toEntity(customerDTO);
        customer=customerRepository.save(customer);
        return  customerMapper.toDto(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Page<CustomerDTO> getCustomerByName(String name, Pageable pageable) {
        if (!Objects.isNull(name)) {
            name = name.trim().replace("%", "!%").replace("_", "!_");
        }
        Page<Customer> customerPageable=customerRepository.findByFullName(name,pageable);
        if(customerPageable != null && !customerPageable.isEmpty()){
            customerPageable.getContent();
        }
        return  customerPageable.map(customerMapper::toDto);


    }
    @Autowired
    AccountBankRepository accountBankRepository;
    @Override
    public ResponseData validateCustomer(CustomerDTO customerDTO) {
        ResponseData responseData=new ResponseData();
        if(!Objects.isNull(customerDTO.getAccountBankDTOS())){
            customerDTO.getAccountBankDTOS().forEach(accountBankDTO -> {
                Optional<AccountBank> optionalAccountBankn =accountBankRepository.findById(accountBankDTO.getId());
                if(!optionalAccountBankn.isPresent()){
                    responseData.setMessage(getText("accountbank.existed"));
                    return;
                }
            });
        }
        if(!Objects.isNull(responseData.getMessage())){
            responseData.setErrorCode(Constants.ERROR_CODE.VALIDATE_FAIL);
            return  responseData;
        }
        return null;

    }
}