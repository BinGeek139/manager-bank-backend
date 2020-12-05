package com.ptit.managerbank.dto.response;

import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBankResponseDTO implements Serializable {
    private Integer id;
    private String code;
    private String type;
    private Double balance;
    CustomerDTO customerDTO;
    public static AccountBankResponseDTO fromAccountBankDTO(AccountBankDTO dto){
        AccountBankResponseDTO accountBank = new AccountBankResponseDTO();
        accountBank.setId( dto.getId() );
        accountBank.setCode( dto.getCode() );
        accountBank.setType( dto.getType() );
        accountBank.setBalance( dto.getBalance() );
        accountBank.setCustomerDTO( dto.getCustomer()  );
        return accountBank;
    }
}
