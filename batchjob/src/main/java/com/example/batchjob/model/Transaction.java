package com.example.batchjob.model;

public class Transaction {
    private String accountNumber;
    private Double trxAmount;
    private String description;
    private String trxDate;
    private String trxTime;
    private String customerId;

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(Double trxAmount) {
        this.trxAmount = trxAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getTrxTime() {
        return trxTime;
    }

    public void setTrxTime(String trxTime) {
        this.trxTime = trxTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNumber='" + accountNumber + '\'' +
                ", trxAmount=" + trxAmount +
                ", description='" + description + '\'' +
                ", trxDate='" + trxDate + '\'' +
                ", trxTime='" + trxTime + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}