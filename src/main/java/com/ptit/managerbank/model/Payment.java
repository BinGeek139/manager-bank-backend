package com.ptit.managerbank.model;

import javax.persistence.*;

@Entity
@Table
public class Payment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id_send", nullable = false)
    private  AccountBank accountBankSend;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id_receive", nullable = false)
    private AccountBank accountBankReceive;
    private Double amount;
    private  String code;

    public AccountBank getAccountBankSend() {
        return accountBankSend;
    }

    public void setAccountBankSend(AccountBank accountBankSend) {
        this.accountBankSend = accountBankSend;
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

