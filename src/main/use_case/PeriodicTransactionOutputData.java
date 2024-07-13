package use_case;

public class PeriodicTransactionOutputData {
    private boolean success;
    private String message;  // output msg
    private float transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String recurrence;

    public PeriodicTransactionOutputData {
        this.success = success;
        this.message = message;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.recurrence = recurrence;
    }

    // getters
    public float getTransactionAmount() {
        return transactionAmount;
    }
    public String getTransactionDate() {
        return transactionDate;
    }
    public String getTransactionDescription() {
        return transactionDescription;
    }
    public String getRecurrence() {
        return recurrence;
    }

    // setters
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}