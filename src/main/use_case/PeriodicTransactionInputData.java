package use_case;

public class PeriodicTransactionInputData {

    private float transactionAmount;
    private final String identification;
    private String transactionDate;
    private String transactionDescription;
    private String recurrence;

    public PeriodicTransactionInputData(String identification, float transactionAmount, String transactionDate, String transactionDescription, String recurrence) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.recurrence = recurrence;
        this.identification = identification;

    }

    public String getIdentification() {
        return identification;
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