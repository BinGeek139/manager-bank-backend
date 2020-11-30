package com.ptit.managerbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBankDTO {
    private Integer id;
    @Pattern(regexp = "^\\d+$",message = "accountBank.code" )
    private String code;
    private String type;
    private CustomerDTO customer;
}
