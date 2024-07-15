package use_case;

public class PeriodicTransactionOutputData {
    private float transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String recurrence;

    public PeriodicTransactionOutputData(float transactionAmount,
                                         String transactionDate, String transactionDescription, String recurrence) {

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
    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }
}