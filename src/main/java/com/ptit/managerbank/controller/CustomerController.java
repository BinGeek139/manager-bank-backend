package com.ptit.managerbank.controller;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.Constants;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.CustomerDTO;
import com.ptit.managerbank.dto.request.CustomerRequestDTO;
import com.ptit.managerbank.dto.response.CustomerResponseDTO;
import com.ptit.managerbank.service.CustomerService;
import com.ptit.managerbank.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

@RestController()
@RequestMapping("api/customer")
public class CustomerController extends BaseComponent {
    @Autowired
    CustomerService customerService;

    @GetMapping("{id}")
    public ResponseEntity<ResponseData> getCustomer(@PathVariable Integer id, HttpServletRequest request) {
        CustomerDTO customerDTO = customerService.findCustomerById(id);
        ResponseData responseData = null;
        if (Objects.isNull(customerDTO)) {
            responseData = ResponseData.ofNotFound(getText("customer.find.id.failure", request));
        } else {
            responseData = ResponseData.ofSuccess(getText("customer.find.id.success", request), customerDTO);
        }
        return ResponseEntity.ok(responseData);
    }

    @GetMapping()
    public ResponseEntity<ResponseData> getCustomers(String name, Pageable pageable, HttpServletRequest request) {
        Page<CustomerDTO> customerDTOS = customerService.getCustomerByName(name, pageable);
        ResponseData responseData = ResponseData.ofSuccess(getText("customer.find.id.success", request), customerDTOS);
        return ResponseEntity.ok(responseData);
    }
    @Autowired
    StaffService staffService;
    @PostMapping
    public ResponseEntity<ResponseData> addCustomer(@RequestBody @Valid CustomerRequestDTO customerDTO, HttpServletRequest request, Principal principal) {
        String username="";
        if (principal instanceof UserDetails) {
         username = ((UserDetails) principal).getUsername();
        } else {
        username = principal.toString();
        }
        if(!staffService.checkPositionByUsername(username, Constants.NHAN_VIEN_KINH_DOANH)){
            return ResponseEntity.ok(ResponseData.ofFail(getText("customer.save.deny", request)));
        }

        if(customerService.checkCodeExisted(customerDTO.getCode())){
            return ResponseEntity.ok(ResponseData.ofFail(getText("customer.save.code.existed", request)));
        }


        CustomerResponseDTO customerD = customerService.saveCustomer(customerDTO);
        return ResponseEntity.ok(ResponseData.ofSuccess(getText("customer.save.success", request), customerD));
    }

    @PutMapping
    public ResponseEntity<ResponseData> updateCustomer(@RequestBody CustomerDTO customerDTO, HttpServletRequest request) {

        if(customerService.checkCodeExisted(customerDTO.getCode())){
            return ResponseEntity.ok(ResponseData.ofFail(getText("customer.save.code.existed", request)));
        }

            customerDTO = customerService.updateCustomer(customerDTO);
            return ResponseEntity.ok(ResponseData.ofSuccess(getText("customer.update.success"), customerDTO));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseData> disableCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(ResponseData.ofSuccess(getText("customer.disable.true", id.toString())));
    }
}   

