package com.ptit.managerbank.service.imp;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.Constants;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.common.TypeAccountBank;
import com.ptit.managerbank.dto.CustomerDTO;
import com.ptit.managerbank.dto.request.CustomerRequestDTO;
import com.ptit.managerbank.dto.response.CustomerResponseDTO;
import com.ptit.managerbank.dto.response.CustomerStatistic;
import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.Customer;
import com.ptit.managerbank.repository.AccountBankRepository;
import com.ptit.managerbank.repository.CustomerRepository;
import com.ptit.managerbank.service.CustomerService;
import com.ptit.managerbank.service.mapper.AccountBankMapper;
import com.ptit.managerbank.service.mapper.CustomerMapper;
import com.ptit.managerbank.service.mapper.CustomerRequestMapper;
import com.ptit.managerbank.service.mapper.CustomerResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class CustomerServiceImpl  extends BaseComponent implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerRequestMapper customerRequestMapper;

    @Autowired
    CustomerResponseMapper customerResponseMapper;


    @Override
    public CustomerDTO findCustomerById(Integer id) {
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            return customerMapper.toDto(optionalCustomer.get());
        }
        return null;
    }

    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerDTO) {
        Customer customer=customerRequestMapper.toEntity(customerDTO);
        customer=customerRepository.save(customer);
        return  customerResponseMapper.toDto(customer);
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
        } else {
            name="%";
        }
        Page<Customer> customerPageable=customerRepository.findByFullName(name,pageable);
        if(customerPageable != null && !customerPageable.isEmpty()){
            customerPageable.getContent();
        }
        return  customerPageable.map(customerMapper::toDto);


    }

    @Autowired
    AccountBankRepository accountBankRepository;
    @Autowired
    AccountBankMapper accountBankMapper;



//    @Override
//    public ResponseData validateCustomer(CustomerDTO customerDTO) {
//        ResponseData responseData=new ResponseData();
//
//
//
//
//
//
//        if(!Objects.isNull(responseData.getMessage())){
//            responseData.setErrorCode(Constants.ERROR_CODE.VALIDATE_FAIL);
//            return  responseData;
//        }
//        return null;
//
//    }

    @Override
    public boolean checkCodeExisted(String code) {
        Optional<Customer> optionalCustomer=customerRepository.findFirstByCode(code);
        return optionalCustomer.isPresent();
    }
    @Transactional
    @Override
    public List<CustomerStatistic> statisticCustomerMaxSaving(Integer number) {
        List<Customer> customers=customerRepository.findAll();
        List<CustomerStatistic> customerStatistics=new ArrayList<>();
        for (Customer customer:customers
             ) {
            double sumAmount=0;
            for (AccountBank accountBank : customer.getAccountBanks()) {
                if(accountBank.getType().equals(TypeAccountBank.SAVE_ACCOUNT_BANK)){
                    sumAmount+=accountBank.getBalance();
                }

            }
            CustomerStatistic customerStatistic=new CustomerStatistic();
            customerStatistic.setCustomer(customerMapper.toDto(customer));
            customerStatistic.setSumAmount(sumAmount);

        }
        customerStatistics.sort(new Comparator<CustomerStatistic>() {
            @Override
            public int compare(CustomerStatistic o1, CustomerStatistic o2) {
                return o1.getSumAmount().compareTo(o2.getSumAmount());
            }
        });

        return customerStatistics.subList(0,number>customerStatistics.size() ? customerStatistics.size()-1: number-1 ) ;
    }


}
