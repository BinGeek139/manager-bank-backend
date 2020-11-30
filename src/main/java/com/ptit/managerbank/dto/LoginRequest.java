package com.ptit.managerbank.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class LoginRequest {
    @NotBlank(message="login.request.username.blank")
    private String username;

    @NotBlank(message = "login.request.password.blank")
    private String password;
}
