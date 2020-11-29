package com.ptit.managerbank.dto;

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
public class StaffDTO implements Serializable {
    private Integer id;
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]*",message = "staff.validate.code")
    private String code;
    private String idCard;
    @NotBlank(message = "staff.validate.fullName")
    private String fullName;
    @Past(message = "staff.validate.date")
    private Date dob;
    private String address;
    private Integer level;
    private Integer seniority;
    @NotBlank(message = "staff.validate.username")
    private String userName;
    @NotBlank(message = "staff.validate.password")
    private String password;
    @Pattern(regexp = "USER|ADMIN",message = "staff.validate.role")
    private String role;
    private Set<PositionDTO> positions;


}
