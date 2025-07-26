package com.bank.bean;

import com.bank.enums.TxnType;

import java.sql.Timestamp;

public class TransferBean {
    private String acc_no;
    private String beneficiary_acc_no;
    private TxnType txnType;
    private double txn_amount;
    private Timestamp txn_date;

    public TransferBean() {
    }

    public TransferBean(String acc_no, Timestamp txn_date, double txn_amount, TxnType txnType, String beneficiary_acc_no) {
        this.acc_no = acc_no;
        this.txn_date = txn_date;
        this.txn_amount = txn_amount;
        this.txnType = txnType;
        this.beneficiary_acc_no = beneficiary_acc_no;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public Timestamp getTxn_date() {
        return txn_date;
    }

    public void setTxn_date(Timestamp txn_date) {
        this.txn_date = txn_date;
    }

    public double getTxn_amount() {
        return txn_amount;
    }

    public void setTxn_amount(double txn_amount) {
        this.txn_amount = txn_amount;
    }

    public TxnType getTxnType() {
        return txnType;
    }

    public void setTxnType(TxnType txnType) {
        this.txnType = txnType;
    }

    public String getBeneficiary_acc_no() {
        return beneficiary_acc_no;
    }

    public void setBeneficiary_acc_no(String beneficiary_acc_no) {
        this.beneficiary_acc_no = beneficiary_acc_no;
    }
}
