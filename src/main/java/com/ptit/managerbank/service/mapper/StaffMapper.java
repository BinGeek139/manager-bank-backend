package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.StaffDTO;
import com.ptit.managerbank.model.Staff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper  extends  EntityMapper<StaffDTO, Staff> {
}
