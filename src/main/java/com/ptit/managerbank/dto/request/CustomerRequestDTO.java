package com.ptit.managerbank.dto.request;

import com.ptit.managerbank.dto.AccountBankDTO;
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
public class CustomerRequestDTO implements Serializable {
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]*",message = "staff.validate.code")
    private String code;
    @NotBlank(message = "staff.validate.fullName")
    private String name;
    @Past(message = "staff.validate.date")
    private Date dob;
    private String address;
}
