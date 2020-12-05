package com.ptit.managerbank.dto.response;

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
public class CustomerResponseDTO implements Serializable {
    private Integer id;
    private String code;
    private String name;
    private Date dob;
    private String address;
}
