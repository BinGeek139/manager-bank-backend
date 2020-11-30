package com.ptit.managerbank.dto;

import com.ptit.managerbank.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingAccountDTO {

    private Integer id;
    @Pattern(regexp = "^\\d+$",message = "accountBank.code" )
    private String code;
    private String type;
    @Min(value = 0,message = "accountBank.balance")
    private Double balance;
    @Min(value = 0,message = "accountBank.interestRate")
    private float interestRate;
    @Min(value = 0,message = "accountBank.minBalance")
    private Double minBalance;
    private CustomerDTO customer;

}
