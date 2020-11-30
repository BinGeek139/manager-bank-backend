package com.ptit.managerbank.service.imp;


import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.Constants;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.SavingAccountDTO;
import com.ptit.managerbank.dto.CustomerDTO;
import com.ptit.managerbank.model.SavingAccount;
import com.ptit.managerbank.model.Customer;
import com.ptit.managerbank.repository.SavingAccountRepository;
import com.ptit.managerbank.repository.CustomerRepository;
import com.ptit.managerbank.service.SavingAccountService;
import com.ptit.managerbank.service.mapper.SavingAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class SavingAccountServiceImpl extends BaseComponent implements SavingAccountService {
    @Autowired
    SavingAccountMapper savingAccountMapper;
    @Autowired
    SavingAccountRepository savingAccountRepository;

    @Override
    public SavingAccountDTO findAccountBankById(Integer id) {
        Optional<SavingAccount> optionalAccountBank= savingAccountRepository.findById(id);
        if(optionalAccountBank.isPresent()){
            return savingAccountMapper.toDto(optionalAccountBank.get());
        }
        return null;
    }

    @Override
    public SavingAccountDTO saveAccountBank(SavingAccountDTO savingAccountDTO) {
        SavingAccount savingAccount = savingAccountMapper.toEntity(savingAccountDTO);
        savingAccount = savingAccountRepository.save(savingAccount);
        return  savingAccountMapper.toDto(savingAccount);
    }

    @Override
    public SavingAccountDTO updateAccountBank(SavingAccountDTO savingAccountDTO) {
        SavingAccount savingAccount = savingAccountMapper.toEntity(savingAccountDTO);
        savingAccount = savingAccountRepository.save(savingAccount);
        return  savingAccountMapper.toDto(savingAccount);
    }

    @Override
    public void deleteAccountBank(Integer id) {
        savingAccountRepository.deleteById(id);
    }

    @Override
    public Page<SavingAccountDTO> getAccountBankByCode(String code, Pageable pageable) {
        if (!Objects.isNull(code)) {
            code = code.trim().replace("%", "!%").replace("_", "!_");
        }
        Page<SavingAccount> accountBankPageable= savingAccountRepository.findByCode(code,pageable);
        if(accountBankPageable != null && !accountBankPageable.isEmpty()){
            accountBankPageable.getContent();
        }
        return  accountBankPageable.map(savingAccountMapper::toDto);


    }
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public ResponseData validateAccountBank(SavingAccountDTO savingAccountDTO) {
        ResponseData responseData=new ResponseData();
        CustomerDTO customerDTO= savingAccountDTO.getCustomer();
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
