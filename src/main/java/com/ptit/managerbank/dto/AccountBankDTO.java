package com.ptit.managerbank.dto;

import com.ptit.managerbank.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBankDTO {
    private Integer id;
    private String code;
    private String type;
    private Double balance;
    private float interestRate;
    private Double minBalance;
    private CustomerDTO customer;
    private Double creditLimit;

}
