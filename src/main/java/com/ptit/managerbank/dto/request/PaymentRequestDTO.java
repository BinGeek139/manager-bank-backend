package com.ptit.managerbank.dto.request;

import com.ptit.managerbank.dto.AccountBankDTO;
import com.ptit.managerbank.model.AccountBank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO implements Serializable {
    @NotNull(message = "payment.account.send.null")
    private AccountBankRequestDTO accountBankSend;
    @NotNull(message = "payment.account.receive.null")
    private AccountBankRequestDTO accountBankReceive;
    @Min(value = 0,message = "payment.amount.min")
    private Double amount;

}
