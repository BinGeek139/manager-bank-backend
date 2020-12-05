package com.ptit.managerbank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditAccountRequestDTO extends AccountBankRequestDTO implements Serializable {
    private Double creditLimit;
}
