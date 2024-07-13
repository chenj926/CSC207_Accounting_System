package use_case;

public class PeriodicTransactionInputData {

    private float transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String recurrence;

    public periodicTransactionInputData(float transactionAmount, String transactionDate, String transactionDescription, String recurrence) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.recurrence = recurrence;

    }

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

}