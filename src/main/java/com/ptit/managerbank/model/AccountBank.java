package com.ptit.managerbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_bank")
public class AccountBank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String type;
    private Double balance;
    private float interestRate;
    private Double minBalance;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
