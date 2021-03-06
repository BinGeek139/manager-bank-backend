package com.ptit.managerbank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Entity
@Table
public class SavingAccount extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String type;
    private Double balance;
    private float interestRate;
    private Double minBalance;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY, mappedBy = "accountBankSend")
    private Set<Payment> paymentSend;
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY, mappedBy = "accountBankReceive")
    private Set<Payment> paymentReceive;

    public Set<Payment> getPaymentReceive() {
        return paymentReceive;
    }

    public void setPaymentReceive(Set<Payment> paymentReceive) {
        this.paymentReceive = paymentReceive;
    }

    public Set<Payment> getPaymentSend() {
        return paymentSend;
    }

    public void setPaymentSend(Set<Payment> paymentSend) {
        this.paymentSend = paymentSend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public Double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(Double minBalance) {
        this.minBalance = minBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

