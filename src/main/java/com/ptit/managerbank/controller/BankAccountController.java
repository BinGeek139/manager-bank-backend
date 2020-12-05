package com.ptit.managerbank.controller;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.common.TypeAccountBank;
import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.dto.BillDepositDTO;
import com.ptit.managerbank.dto.request.CreditAccountRequestDTO;
import com.ptit.managerbank.dto.request.SavingAccountRequestDTO;
import com.ptit.managerbank.dto.request.TransferRequest;
import com.ptit.managerbank.dto.response.AccountBankResponseDTO;
import com.ptit.managerbank.service.AccountBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;
@RestController
@RequestMapping("api/account-bank")
public class BankAccountController  extends BaseComponent {
    @Autowired
    AccountBankService accountBankService;
    @GetMapping("{id}")
    public ResponseEntity<ResponseData> getAccountBank(@PathVariable Integer id, HttpServletRequest request){
        AccountBankDTO accountBankDTO = accountBankService.findAccountBankById(id);
        ResponseData responseData=null;
        if(Objects.isNull(accountBankDTO)){
            responseData=ResponseData.ofNotFound(getText("accountBank.find.id.failure",request));
        } else {
            responseData=ResponseData.ofSuccess(getText("accountBank.find.id.success",request), accountBankDTO);
        }
        return ResponseEntity.ok(responseData);
    }

    @GetMapping()
    public ResponseEntity<ResponseData> getAccountBanks(String code, Pageable pageable, HttpServletRequest request){
        Page<AccountBankDTO> accountBankDTOS= accountBankService.getAccountBankByCode(code,pageable);
        ResponseData  responseData=ResponseData.ofSuccess(getText("accountBank.find.id.success",request),accountBankDTOS);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("credit")
    public ResponseEntity<ResponseData> addAccountBank(@RequestBody @Valid CreditAccountRequestDTO accountBankDTO, HttpServletRequest request){

        ResponseData responseData= accountBankService.validateAccountBank(accountBankDTO);
        if(Objects.isNull(responseData)){
            AccountBankDTO bankDTO=new AccountBankDTO();
            bankDTO.setType(TypeAccountBank.CREDIT_ACCOUNT_BANK.toString());
            bankDTO.setCustomer(accountBankDTO.getCustomerDTO());
            bankDTO.setBalance(accountBankDTO.getBalance());
            bankDTO.setCreditLimit(accountBankDTO.getCreditLimit());
           AccountBankDTO accountBank = accountBankService.saveAccountBank(bankDTO);

            return ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.save.success",request), AccountBankResponseDTO.fromAccountBankDTO(accountBank)));
        } else {
            return ResponseEntity.ok(responseData);
        }
    }
    @PostMapping("saving")
    public ResponseEntity<ResponseData> addSavingAccountBank(@RequestBody @Valid SavingAccountRequestDTO accountBankDTO, HttpServletRequest request){

        ResponseData responseData= accountBankService.validateAccountBank(accountBankDTO);
        if(Objects.isNull(responseData)){
            AccountBankDTO bankDTO=new AccountBankDTO();
            bankDTO.setType(TypeAccountBank.SAVE_ACCOUNT_BANK.toString());
            bankDTO.setCustomer(accountBankDTO.getCustomerDTO());
            bankDTO.setBalance(accountBankDTO.getBalance());
            bankDTO.setInterestRate(accountBankDTO.getInterestRate());
            bankDTO.setMinBalance(accountBankDTO.getMinBalance());
            AccountBankDTO accountBank = accountBankService.saveAccountBank(bankDTO);
            return ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.save.success",request), accountBankDTO));
        } else {
            return ResponseEntity.ok(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData> updateAccountBank(@RequestBody AccountBankDTO accountBankDTO){

        ResponseData responseData= accountBankService.validateUpdateAccountBank(accountBankDTO);
        if(Objects.isNull(responseData)){
            accountBankDTO = accountBankService.updateAccountBank(accountBankDTO);
            return ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.update.success"), accountBankDTO));
        } else {
            return ResponseEntity.ok(responseData);
        }

    }
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseData> disableAccountBank(@PathVariable Integer id){
        accountBankService.deleteAccountBank(id);
        return ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.disable.true",id.toString())));
    }

    @PostMapping("deposit")
    public ResponseEntity<ResponseData> deposit(@RequestBody  String code,@RequestBody  Double amount){
        BillDepositDTO billDeposit=accountBankService.deposit(code,amount);
        if(!Objects.isNull(billDeposit)){
            return  ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.deposit.failure")));
        } else {
            return  ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.deposit.success"),billDeposit));
        }
    }
    @PostMapping("withdraw")
    public ResponseEntity<ResponseData> withdraw(@RequestBody  String code,@RequestBody  Double amount){
        return  ResponseEntity.ok(accountBankService.withdraw(code,amount));
    }
    @PostMapping("transfer")
    public ResponseEntity<ResponseData> transfer(@RequestBody TransferRequest transferRequest){


        return null;
    }



}
