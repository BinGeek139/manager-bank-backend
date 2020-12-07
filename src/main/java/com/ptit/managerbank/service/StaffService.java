package com.ptit.managerbank.service;

import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.StaffDTO;
import com.ptit.managerbank.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface StaffService {
    StaffDTO findStaffById(Integer id);
    StaffDTO saveStaff(StaffDTO staffDTO);
    StaffDTO updateStaff(StaffDTO staffDTO);
    boolean deleteStaff(Integer id);
    Page<StaffDTO> getStaffByName(String name, Pageable pageable);
    ResponseData validateStaff(StaffDTO staffDTO);
    boolean checkPositionByUsername(String username,String position);
    Double payrollBusinessman(Integer id, Date start, Date end);

}
