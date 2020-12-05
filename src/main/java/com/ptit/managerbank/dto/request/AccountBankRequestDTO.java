package com.ptit.managerbank.dto.request;

import com.ptit.managerbank.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBankRequestDTO implements Serializable {

    @Min(value = 0,message = "accountBank.balance")
    private Double balance;
    CustomerDTO customerDTO;

}
