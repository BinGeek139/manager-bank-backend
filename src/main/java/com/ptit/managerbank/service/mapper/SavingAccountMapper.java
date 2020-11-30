package com.ptit.managerbank.service.mapper;

import com.ptit.managerbank.dto.SavingAccountDTO;
import com.ptit.managerbank.model.SavingAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SavingAccountMapper extends  EntityMapper<SavingAccountDTO, SavingAccount> {

}
