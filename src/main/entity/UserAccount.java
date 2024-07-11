package main.entity;

import util.*;

public abstract class UserAccount implements Transaction {
    private float totalCurrentBalance;
    private float totalIncome;
    private float totalOutflow;
    private String identification;

    // Constructors
    public UserAccount(){
        this.totalCurrentBalance = 0.0f;
        this.totalIncome = 0.0f;
        this.totalOutflow = 0.0f;
        this.identification = "";
    }
    public UserAccount(float balance, float income, float outflow, String identification) {
        this.totalCurrentBalance = balance;
        this.totalIncome = income;
        this.totalOutflow = outflow;
        this.identification = identification;
    }

    // Getters
    public float getTotalCurrentBalance() {
        return this.totalCurrentBalance;
    }
    public float getTotalIncome() {
        return this.totalIncome;
    }
    public float getTotalOutflow() {
        return this.totalOutflow;
    }
    public String getIdentification() {
        return this.identification;
    }

    // Setters
    public void setTotalCurrentBalance(float balance) {
        this.totalCurrentBalance = balance;
    }
    public void setTotalIncome(float income) {
        this.totalIncome = income;
    }
    public void setTotalOutflow(float outflow) {
        this.totalOutflow = outflow;
    }
    public void setIdentification(String indentification) {
        this.identification = indentification;
    }

    // implement the interface's RecordTransaction method
    @Override
    public void RecordTransaction(float amount) {
        if (amount >= 0.0) {
            this.totalIncome += amount;
        }
        else {
            this.totalOutflow += Math.abs(amount);  // let the outflow to p+ first
        }
        this.totalCurrentBalance += amount;
    }
}