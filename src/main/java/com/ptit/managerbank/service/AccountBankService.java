package com.ptit.managerbank.service;

import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.dto.BillDepositDTO;
import com.ptit.managerbank.dto.BillWithDrawDTO;
import com.ptit.managerbank.dto.request.AccountBankRequestDTO;
import com.ptit.managerbank.dto.request.PaymentRequestDTO;
import com.ptit.managerbank.dto.request.SavingAccountRequestDTO;
import com.ptit.managerbank.dto.request.TransferRequest;
import com.ptit.managerbank.dto.response.PaymentResponseDTO;
import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.BillDeposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountBankService {
    AccountBankDTO findAccountBankById(Integer id);
    AccountBankDTO findAccountBankByCode(String code);
    AccountBankDTO saveAccountBank(AccountBankDTO accountBankDTO);
    AccountBankDTO updateAccountBank(AccountBankDTO accountBankDTO);
    void deleteAccountBank(Integer id);
    Page<AccountBankDTO> getAccountBankByCode(String code, Pageable pageable);
    ResponseData validateAccountBank(AccountBankRequestDTO accountBankDTO);
    ResponseData validateSavingAccountBank(SavingAccountRequestDTO accountBankDTO);
    ResponseData validateUpdateAccountBank(AccountBankDTO accountBankDTO);
    BillDepositDTO deposit(String code, Double amount);
    ResponseData withdraw(String code, Double amount);
    ResponseData transfer(TransferRequest transferRequest);


}
