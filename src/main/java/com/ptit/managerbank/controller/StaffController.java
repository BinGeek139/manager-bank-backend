package com.ptit.managerbank.controller;

import com.ptit.managerbank.common.BaseComponent;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.StaffDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/staff")
public class StaffController extends BaseComponent {
    @GetMapping("{id}")
    public ResponseEntity<ResponseData> getStaff(@PathVariable Integer id){
        return ResponseEntity.ok(ResponseData.ofSuccess());
    }

    @GetMapping()
    public ResponseEntity<ResponseData> getStaffs(String name){
        return ResponseEntity.ok(ResponseData.ofSuccess("getStaff"));
    }

    @PostMapping
    public ResponseEntity<ResponseData> addStaff(@RequestBody StaffDTO staffDTO){
        return ResponseEntity.ok(ResponseData.ofSuccess());

    }
    @PutMapping
    public ResponseEntity<ResponseData> updateStaff(@RequestBody StaffDTO staffDTO){
        return ResponseEntity.ok(ResponseData.ofSuccess());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseData> disableStaff(@PathVariable Integer id){
        return ResponseEntity.ok(ResponseData.ofSuccess(getText("staff.disable.true",id.toString())));
    }
}
