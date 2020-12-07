package com.ptit.managerbank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealReponse  implements Serializable {
    private  String name ;
    private String account;
    private double amount;
}
