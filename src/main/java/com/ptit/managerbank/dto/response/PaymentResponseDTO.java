package com.ptit.managerbank.dto.response;

import com.ptit.managerbank.dto.request.AccountBankRequestDTO;
import com.ptit.managerbank.dto.request.CreditAccountRequestDTO;
import com.ptit.managerbank.dto.request.SavingAccountRequestDTO;
import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.SavingAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO  implements Serializable {
    private Integer id;
    private SavingAccountRequestDTO accountBankSend;
    private CreditAccountRequestDTO accountBankReceive;
    private Double amount;
    private  String code;
    private Date createAt;


}
