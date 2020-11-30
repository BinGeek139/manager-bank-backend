package com.ptit.managerbank.service.imp;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.Constants;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.dto.CustomerDTO;
import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.Customer;
import com.ptit.managerbank.repository.AccountBankRepository;
import com.ptit.managerbank.repository.CustomerRepository;

import com.ptit.managerbank.service.AccountBankService;
import com.ptit.managerbank.service.mapper.AccountBankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
@Component
public class AccountBankServiceImpl extends BaseComponent implements AccountBankService {
    @Autowired
    AccountBankMapper accountBankMapper;
    @Autowired
    AccountBankRepository accountBankRepository;

    @Override
    public AccountBankDTO findAccountBankById(Integer id) {
        Optional<AccountBank> optionalAccountBank= accountBankRepository.findById(id);
        if(optionalAccountBank.isPresent()){
            return accountBankMapper.toDto(optionalAccountBank.get());
        }
        return null;
    }

    @Override
    public AccountBankDTO saveAccountBank(AccountBankDTO accountBankDTO) {
        AccountBank accountBank = accountBankMapper.toEntity(accountBankDTO);
        accountBank = accountBankRepository.save(accountBank);
        return  accountBankMapper.toDto(accountBank);
    }

    @Override
    public AccountBankDTO updateAccountBank(AccountBankDTO accountBankDTO) {
        AccountBank accountBank = accountBankMapper.toEntity(accountBankDTO);
        accountBank = accountBankRepository.save(accountBank);
        return  accountBankMapper.toDto(accountBank);
    }

    @Override
    public void deleteAccountBank(Integer id) {
        accountBankRepository.deleteById(id);
    }

    @Override
    public Page<AccountBankDTO> getAccountBankByCode(String code, Pageable pageable) {
        if (!Objects.isNull(code)) {
            code = code.trim().replace("%", "!%").replace("_", "!_");
        }
        Page<AccountBank> accountBankPageable= accountBankRepository.findByCode(code,pageable);
        if(accountBankPageable != null && !accountBankPageable.isEmpty()){
            accountBankPageable.getContent();
        }
        return  accountBankPageable.map(accountBankMapper::toDto);


    }
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public ResponseData validateAccountBank(AccountBankDTO accountBankDTO) {
        ResponseData responseData=new ResponseData();
        CustomerDTO customerDTO= accountBankDTO.getCustomer();
        if(!Objects.isNull(customerDTO)){
            Optional<Customer> optionalCustomer = customerRepository.findById(customerDTO.getId());
            if(!optionalCustomer.isPresent()){
                responseData.setMessage(getText("customer.existed"));
            }}
        if(!Objects.isNull(responseData.getMessage())){
            responseData.setErrorCode(Constants.ERROR_CODE.VALIDATE_FAIL);
            return  responseData;
        }
        return null;

    }
}

