package com.ptit.managerbank.controller;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.StaffDTO;
import com.ptit.managerbank.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@RestController()
@RequestMapping("api/staff")

public class StaffController extends BaseComponent {
    @Autowired
    StaffService staffService;
    @GetMapping("{id}")
    public ResponseEntity<ResponseData> getStaff(@PathVariable Integer id, HttpServletRequest request){
        StaffDTO staffDTO=staffService.findStaffById(id);
        ResponseData responseData=null;
         if(Objects.isNull(staffDTO)){
             responseData=ResponseData.ofNotFound(getText("staff.find.id.failure",request));
         } else {
             responseData=ResponseData.ofSuccess(getText("staff.find.id.success",request),staffDTO);
         }
        return ResponseEntity.ok(responseData);
    }

    @GetMapping()
    public ResponseEntity<ResponseData> getStaffs(String name, Pageable pageable,HttpServletRequest request){
        Page<StaffDTO> staffDTOS=staffService.getStaffByName(name,pageable);
        ResponseData  responseData=ResponseData.ofSuccess(getText("staff.find.id.success",request),staffDTOS);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData> addStaff( @RequestBody @Valid StaffDTO staffDTO,HttpServletRequest request){
        if(staffDTO.getId() != null){
            return ResponseEntity.ok(ResponseData.ofFail(getText("staff.save.id.already",request)));
        }
        ResponseData responseData=staffService.validateStaff(staffDTO);
       if(Objects.isNull(responseData)){
           staffDTO=staffService.saveStaff(staffDTO);
           return ResponseEntity.ok(ResponseData.ofSuccess(getText("staff.save.success",request),staffDTO));
       } else {
           return ResponseEntity.ok(responseData);
       }



    }
    @PutMapping
    public ResponseEntity<ResponseData> updateStaff(@RequestBody StaffDTO staffDTO){

        ResponseData responseData=staffService.validateStaff(staffDTO);
        if(Objects.isNull(responseData)){
            staffDTO=staffService.saveStaff(staffDTO);
            return ResponseEntity.ok(ResponseData.ofSuccess(getText("staff.update.success"),staffDTO));
        } else {
            return ResponseEntity.ok(responseData);
        }

    }
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseData> disableStaff(@PathVariable Integer id){
        staffService.deleteStaff(id);
        return ResponseEntity.ok(ResponseData.ofSuccess(getText("staff.disable.true",id.toString())));
    }
}
