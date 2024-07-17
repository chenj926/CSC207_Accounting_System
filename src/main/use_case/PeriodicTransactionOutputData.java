package use_case;

import entity.PeriodicInflow;
import entity.PeriodicOutflow;

import java.time.LocalDate;

public class PeriodicTransactionOutputData {
    private float transactionAmount;
    private LocalDate transactionStartDate;
    private LocalDate transactionEndDate;
    private int transactionPeriod;
    private String transactionDescription;
    private LocalDate date;
    private float newBalance;

    public PeriodicTransactionOutputData(PeriodicInflow periodicInflow, float newBalance) {

        this.transactionAmount = periodicInflow.getAmount();
        this.transactionStartDate = periodicInflow.getStartDate();
        this.transactionDescription = periodicInflow.getDescription();
        this.transactionEndDate = periodicInflow.getEndDate();
        this.transactionPeriod = periodicInflow.getPeriod();
//        this.date = date;
        this.newBalance = newBalance;
    }

    public PeriodicTransactionOutputData(PeriodicOutflow periodicOutflow, float newBalance) {

        this.transactionAmount = periodicOutflow.getAmount();
        this.transactionStartDate = periodicOutflow.getStartDate();
        this.transactionDescription = periodicOutflow.getDescription();
        this.transactionEndDate = periodicOutflow.getEndDate();
        this.transactionPeriod = periodicOutflow.getPeriod();
//        this.date = date;
        this.newBalance = newBalance;
    }

    // getters
    public float getTransactionAmount() {
        return this.transactionAmount;
    }
    public LocalDate getTransactionStartDate() {
        return this.transactionStartDate;
    }
    public String getTransactionDescription() {
        return transactionDescription;
    }
    public LocalDate getTransactionEndDate() {
        return this.transactionEndDate;
    }
    public int getTransactionPeriod() {
        return this.transactionPeriod;
    }
    public LocalDate getDate() {return this.date; }
    public float getNewBalance() {return this.newBalance;}

    // setters
    public void setTransactionStartDate(LocalDate transactionStartDate) {
        this. transactionStartDate = transactionStartDate;
    }
    public void setTransactionEndDate(LocalDate transactionEndDate) {
        this. transactionEndDate = transactionEndDate;
    }
    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
    public void setTransactionPeriod(int transactionPeriod) {this.transactionPeriod = transactionPeriod; }
    public void setDate (LocalDate date) { this.date = date;}
    public void setNewBalance(float newBalance) {this.newBalance = newBalance;}
}