package com.ptit.managerbank.service;

import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.StaffDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StaffService {
    StaffDTO findStaffById(Integer id);
    StaffDTO saveStaff(StaffDTO staffDTO);
    StaffDTO updateStaff(StaffDTO staffDTO);
    boolean deleteStaff(Integer id);
    Page<StaffDTO> getStaffByName(String name, Pageable pageable);
    ResponseData validateStaff(StaffDTO staffDTO);
    boolean checkPositionByUsername(String username,String position);
}
