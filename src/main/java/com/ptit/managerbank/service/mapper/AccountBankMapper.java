package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.dto.CustomerDTO;
import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountBankMapper extends  EntityMapper<AccountBankDTO, AccountBank> {
}
