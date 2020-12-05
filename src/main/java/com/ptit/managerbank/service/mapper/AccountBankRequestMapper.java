package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.dto.request.AccountBankRequestDTO;
import com.ptit.managerbank.model.AccountBank;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountBankRequestMapper extends  EntityMapper<AccountBankRequestDTO, AccountBank>{
}
