package com.ptit.managerbank.controller;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.service.AccountBankService;
import com.ptit.managerbank.service.AccountBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@RestController()
@RequestMapping("api/account-bank")
public class AccountBankController extends BaseComponent {
    @Autowired
    AccountBankService accountBankService;
    @GetMapping("{id}")
    public ResponseEntity<ResponseData> getAccountBank(@PathVariable Integer id, HttpServletRequest request){
       AccountBankDTO accountBankDTO=accountBankService.findAccountBankById(id);
        ResponseData responseData=null;
        if(Objects.isNull(accountBankDTO)){
            responseData=ResponseData.ofNotFound(getText("accountBank.find.id.failure",request));
        } else {
            responseData=ResponseData.ofSuccess(getText("accountBank.find.id.success",request),accountBankDTO);
        }
        return ResponseEntity.ok(responseData);
    }

    @GetMapping()
    public ResponseEntity<ResponseData> getAccountBanks(String code, Pageable pageable, HttpServletRequest request){
        Page<AccountBankDTO> accountBankDTOS=accountBankService.getAccountBankByCode(code,pageable);
        ResponseData  responseData=ResponseData.ofSuccess(getText("accountBank.find.id.success",request),accountBankDTOS);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData> addAccountBank(@RequestBody @Valid AccountBankDTO accountBankDTO, HttpServletRequest request){
        if(accountBankDTO.getId() != null){
            return ResponseEntity.ok(ResponseData.ofFail(getText("accountBank.save.id.already",request)));
        }
        ResponseData responseData=accountBankService.validateAccountBank(accountBankDTO);
        if(Objects.isNull(responseData)){
            accountBankDTO=accountBankService.saveAccountBank(accountBankDTO);
            return ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.save.success",request),accountBankDTO));
        } else {
            return ResponseEntity.ok(responseData);
        }



    }
    @PutMapping
    public ResponseEntity<ResponseData> updateAccountBank(@RequestBody AccountBankDTO accountBankDTO){

        ResponseData responseData=accountBankService.validateAccountBank(accountBankDTO);
        if(Objects.isNull(responseData)){
            accountBankDTO=accountBankService.saveAccountBank(accountBankDTO);
            return ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.update.success"),accountBankDTO));
        } else {
            return ResponseEntity.ok(responseData);
        }

    }
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseData> disableAccountBank(@PathVariable Integer id){
        accountBankService.deleteAccountBank(id);
        return ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.disable.true",id.toString())));
    }
}
