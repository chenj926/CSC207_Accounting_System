package use_case;

public class PeriodicTransactionOutputData {
    private float transactionAmount;
    private String transactionDate;
    private String transactionDuration;
    private String transactionDescription;
    private String recurrence;

    public PeriodicTransactionOutputData(float transactionAmount,
                                         String transactionDate, String transactionDescription, String recurrence,
                                         String transactionDuration) {

        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.recurrence = recurrence;
        this.transactionDuration = transactionDuration;
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
    public String getTransactionDuration() {
        return transactionDuration;
    }

    // setters
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }
    public void setTransactionDuration(String transactionDuration) {}
}