package com.ptit.managerbank.service;

import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.AccountBankDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface  AccountBankService {
    AccountBankDTO findAccountBankById(Integer id);
    AccountBankDTO saveAccountBank(AccountBankDTO AccountBankDTO);
    AccountBankDTO updateAccountBank(AccountBankDTO AccountBankDTO);
    void deleteAccountBank(Integer id);
    Page<AccountBankDTO> getAccountBankByCode(String code, Pageable pageable);
    ResponseData validateAccountBank(AccountBankDTO AccountBankDTO);
}
