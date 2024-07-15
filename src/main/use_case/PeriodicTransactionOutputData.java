package use_case;

import java.time.LocalDate;

public class PeriodicTransactionOutputData {
    private float transactionAmount;
    private LocalDate transactionStartDate;
    private LocalDate transactionEndDate;
    private String transactionDuration;
    private String transactionDescription;

    public PeriodicTransactionOutputData(float transactionAmount,
                                         LocalDate transactionStartDate, String transactionDescription,
                                         String transactionDuration, LocalDate transactionEndDate) {

        this.transactionAmount = transactionAmount;
        this.transactionStartDate = transactionStartDate;
        this.transactionDescription = transactionDescription;
        this.transactionEndDate = transactionEndDate;
        this.transactionDuration = transactionDuration;
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
    public String getTransactionDuration() {
        return this.transactionDuration;
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
    public void setTransactionDuration(String transactionDuration) {this.transactionDuration = transactionDuration; }
}