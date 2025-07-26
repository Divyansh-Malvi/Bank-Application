package com.bank.bean;

import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;

import java.sql.Timestamp;

public class AccountBean {
    private String acc_no;
    private String customer_name;
    private String pin;
    private double balance;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Timestamp acc_created;

    public AccountBean() {
    }

    public AccountBean(String acc_no, Timestamp acc_created, AccountStatus accountStatus, AccountType accountType, double balance, String pin, String customer_name) {
        this.acc_no = acc_no;
        this.acc_created = acc_created;
        this.accountStatus = accountStatus;
        this.accountType = accountType;
        this.balance = balance;
        this.pin = pin;
        this.customer_name = customer_name;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Timestamp getAcc_created() {
        return acc_created;
    }

    public void setAcc_created(Timestamp acc_created) {
        this.acc_created = acc_created;
    }
}
