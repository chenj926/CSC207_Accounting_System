package use_case;

import java.time.LocalDate;

public class PeriodicTransactionOutputData {
    private float transactionAmount;
    private LocalDate transactionStartDate;
    private LocalDate transactionEndDate;
    private int transactionPeriod;
    private String transactionDescription;

    public PeriodicTransactionOutputData(float transactionAmount,
                                         LocalDate transactionStartDate, String transactionDescription,
                                         int transactionPeriod, LocalDate transactionEndDate) {

        this.transactionAmount = transactionAmount;
        this.transactionStartDate = transactionStartDate;
        this.transactionDescription = transactionDescription;
        this.transactionEndDate = transactionEndDate;
        this.transactionPeriod = transactionPeriod;
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
}