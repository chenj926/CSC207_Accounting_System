package use_case;

import java.time.LocalDate;

public class PeriodicTransactionOutputData {
    private float transactionAmount;
    private LocalDate transactionStartDate;
    private LocalDate transactionEndDate;
    private int transactionPeriod;
    private String transactionDescription;
    private LocalDate date;

    public PeriodicTransactionOutputData(float transactionAmount,
                                         LocalDate transactionStartDate, String transactionDescription,
                                         int transactionPeriod, LocalDate transactionEndDate, LocalDate date) {

        this.transactionAmount = transactionAmount;
        this.transactionStartDate = transactionStartDate;
        this.transactionDescription = transactionDescription;
        this.transactionEndDate = transactionEndDate;
        this.transactionPeriod = transactionPeriod;
        this.date = date;
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
}