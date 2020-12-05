package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.dto.BillDepositDTO;
import com.ptit.managerbank.dto.BillWithDrawDTO;

import com.ptit.managerbank.model.BillWithdraw;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillWithdrawMapper extends  EntityMapper<BillWithDrawDTO , BillWithdraw> {
}
