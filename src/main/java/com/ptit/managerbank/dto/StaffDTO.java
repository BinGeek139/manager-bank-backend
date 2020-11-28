package com.ptit.managerbank.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class StaffDTO {
    private Integer id;

    private String code;
    private String idCard;
    @NotBlank(message = " không được để trống ")
    private String fullName;
    private Date dob;
    private String address;
}
