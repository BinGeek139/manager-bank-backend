package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.BillDepositDTO;
import com.ptit.managerbank.model.BillDeposit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillDepositMapper extends   EntityMapper<BillDepositDTO, BillDeposit> {
}
