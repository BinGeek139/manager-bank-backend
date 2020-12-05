package com.ptit.managerbank.dto;

import com.ptit.managerbank.model.AccountBank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDepositDTO  implements Serializable {
    private Integer id;
    private AccountBank accountBankSend;
    private Double amount;
    private  String code;
    private Date createAt;
}
