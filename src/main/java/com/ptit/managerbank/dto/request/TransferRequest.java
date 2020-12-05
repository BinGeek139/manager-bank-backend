package com.ptit.managerbank.dto.request;

import com.ptit.managerbank.dto.AccountBankDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest implements Serializable {
    @NotNull(message = "account.code.send")
    @Pattern(regexp = "^[0-9]*$",message = "account.code.send")
    private String accountCodeSend;
    @NotNull(message = "account.code.receive")
    @Pattern(regexp = "^[0-9]*$",message = "account.code.receive")
    private String accountCodeReceive;
    private double amount;

}
