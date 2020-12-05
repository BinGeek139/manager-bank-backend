package com.ptit.managerbank.dto;

import com.ptit.managerbank.model.AccountBank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillWithDrawDTO {
    private Integer id;
    private AccountBank accountBankReceive;
    private Double amount;
    private  String code;
    private Date createAt;
}
