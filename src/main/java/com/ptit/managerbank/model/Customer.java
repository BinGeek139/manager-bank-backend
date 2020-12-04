package com.ptit.managerbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Customer extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private Date dob;
    private String address;
    @OneToMany(targetEntity = SavingAccount.class,cascade = CascadeType.PERSIST, mappedBy = "customer")
    private Set<SavingAccount> savingAccounts;
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<AccountBank> accountBanks;
}
