package com.ptit.managerbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class AccountBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String type;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
   private Customer customer;
}
