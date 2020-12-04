package com.ptit.managerbank.service;

import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.model.AccountBank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountBankService {
    AccountBankDTO findAccountBankById(Integer id);
    AccountBankDTO saveAccountBank(AccountBankDTO accountBankDTO);
    AccountBankDTO updateAccountBank(AccountBankDTO accountBankDTO);
    void deleteAccountBank(Integer id);
    Page<AccountBankDTO> getAccountBankByCode(String code, Pageable pageable);
    ResponseData validateAccountBank(AccountBankDTO accountBankDTO);
//    boolean paymentAccountToAccount(AccountBank accountSend,AccountBank accountReceive,Double amount);

}
