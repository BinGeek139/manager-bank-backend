package com.ptit.managerbank.dto;

import com.ptit.managerbank.model.AccountBank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable {
    private Integer id;
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]*",message = "staff.validate.code")
    private String code;
    @NotBlank(message = "staff.validate.fullName")
    private String name;
    @Past(message = "staff.validate.date")
    private Date dob;
    private String address;
    private Set<SavingAccountDTO> savingAccountDTOS;
    private Set<AccountBankDTO> accountBanks;
}

