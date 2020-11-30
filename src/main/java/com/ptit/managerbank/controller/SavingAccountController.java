package com.ptit.managerbank.controller;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.SavingAccountDTO;
import com.ptit.managerbank.service.SavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("api/saving-account")
public class SavingAccountController extends BaseComponent {
    @Autowired
    SavingAccountService savingAccountService;
    @GetMapping("{id}")
    public ResponseEntity<ResponseData> getAccountBank(@PathVariable Integer id, HttpServletRequest request){
       SavingAccountDTO savingAccountDTO = savingAccountService.findAccountBankById(id);
        ResponseData responseData=null;
        if(Objects.isNull(savingAccountDTO)){
            responseData=ResponseData.ofNotFound(getText("accountBank.find.id.failure",request));
        } else {
            responseData=ResponseData.ofSuccess(getText("accountBank.find.id.success",request), savingAccountDTO);
        }
        return ResponseEntity.ok(responseData);
    }

    @GetMapping()
    public ResponseEntity<ResponseData> getAccountBanks(String code, Pageable pageable, HttpServletRequest request){
        Page<SavingAccountDTO> accountBankDTOS= savingAccountService.getAccountBankByCode(code,pageable);
        ResponseData  responseData=ResponseData.ofSuccess(getText("accountBank.find.id.success",request),accountBankDTOS);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData> addAccountBank(@RequestBody @Valid SavingAccountDTO savingAccountDTO, HttpServletRequest request){
        if(savingAccountDTO.getId() != null){
            return ResponseEntity.ok(ResponseData.ofFail(getText("accountBank.save.id.already",request)));
        }
        ResponseData responseData= savingAccountService.validateAccountBank(savingAccountDTO);
        if(Objects.isNull(responseData)){
            savingAccountDTO = savingAccountService.saveAccountBank(savingAccountDTO);
            return ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.save.success",request), savingAccountDTO));
        } else {
            return ResponseEntity.ok(responseData);
        }



    }
    @PutMapping
    public ResponseEntity<ResponseData> updateAccountBank(@RequestBody SavingAccountDTO savingAccountDTO){

        ResponseData responseData= savingAccountService.validateAccountBank(savingAccountDTO);
        if(Objects.isNull(responseData)){
            savingAccountDTO = savingAccountService.saveAccountBank(savingAccountDTO);
            return ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.update.success"), savingAccountDTO));
        } else {
            return ResponseEntity.ok(responseData);
        }

    }
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseData> disableAccountBank(@PathVariable Integer id){
        savingAccountService.deleteAccountBank(id);
        return ResponseEntity.ok(ResponseData.ofSuccess(getText("accountBank.disable.true",id.toString())));
    }
}
