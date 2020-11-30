package com.ptit.managerbank.service;

import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.SavingAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SavingAccountService {
    SavingAccountDTO findAccountBankById(Integer id);
    SavingAccountDTO saveAccountBank(SavingAccountDTO SavingAccountDTO);
    SavingAccountDTO updateAccountBank(SavingAccountDTO SavingAccountDTO);
    void deleteAccountBank(Integer id);
    Page<SavingAccountDTO> getAccountBankByCode(String code, Pageable pageable);
    ResponseData validateAccountBank(SavingAccountDTO SavingAccountDTO);
}
