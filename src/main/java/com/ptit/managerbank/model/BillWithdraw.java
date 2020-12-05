package com.ptit.managerbank.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class BillWithdraw extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id_receive", nullable = false)
    private  AccountBank accountBankReceive;
    private Double amount;
    private  String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountBank getAccountBankReceive() {
        return accountBankReceive;
    }

    public void setAccountBankReceive(AccountBank accountBankReceive) {
        this.accountBankReceive = accountBankReceive;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
