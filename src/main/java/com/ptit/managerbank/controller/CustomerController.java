package com.ptit.managerbank.controller;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.CustomerDTO;
import com.ptit.managerbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@RestController()
@RequestMapping("api/customer")
public class CustomerController extends BaseComponent {
    @Autowired
    CustomerService customerService;
    @GetMapping("{id}")
    public ResponseEntity<ResponseData> getCustomer(@PathVariable Integer id, HttpServletRequest request){
        CustomerDTO customerDTO=customerService.findCustomerById(id);
        ResponseData responseData=null;
        if(Objects.isNull(customerDTO)){
            responseData=ResponseData.ofNotFound(getText("customer.find.id.failure",request));
        } else {
            responseData=ResponseData.ofSuccess(getText("customer.find.id.success",request),customerDTO);
        }
        return ResponseEntity.ok(responseData);
    }

    @GetMapping()
    public ResponseEntity<ResponseData> getCustomers(String name, Pageable pageable, HttpServletRequest request){
        Page<CustomerDTO> customerDTOS=customerService.getCustomerByName(name,pageable);
        ResponseData  responseData=ResponseData.ofSuccess(getText("customer.find.id.success",request),customerDTOS);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData> addCustomer(@RequestBody @Valid CustomerDTO customerDTO, HttpServletRequest request){
        if(customerDTO.getId() != null){
            return ResponseEntity.ok(ResponseData.ofFail(getText("customer.save.id.already",request)));
        }
        ResponseData responseData=customerService.validateCustomer(customerDTO);
        if(Objects.isNull(responseData)){
            customerDTO=customerService.saveCustomer(customerDTO);
            return ResponseEntity.ok(ResponseData.ofSuccess(getText("customer.save.success",request),customerDTO));
        } else {
            return ResponseEntity.ok(responseData);
        }



    }
    @PutMapping
    public ResponseEntity<ResponseData> updateCustomer(@RequestBody CustomerDTO customerDTO){

        ResponseData responseData=customerService.validateCustomer(customerDTO);
        if(Objects.isNull(responseData)){
            customerDTO=customerService.saveCustomer(customerDTO);
            return ResponseEntity.ok(ResponseData.ofSuccess(getText("customer.update.success"),customerDTO));
        } else {
            return ResponseEntity.ok(responseData);
        }

    }
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseData> disableCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(ResponseData.ofSuccess(getText("customer.disable.true",id.toString())));
    }
}   

