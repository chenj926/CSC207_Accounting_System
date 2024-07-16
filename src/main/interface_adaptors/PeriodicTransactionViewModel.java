package interface_adaptors;

public class PeriodicTransactionViewModel extends TransactionViewModel {
    public final String TITLE_LABEL = "Periodic Transaction";
    public final String TRANSACTION_AMOUNT_LABEL = "Transaction Amount";
    public final String TRANSACTION_DATE_LABEL = "Transaction Date";
    public final String TRANSACTION_DESCRIPTION_LABEL = "Transaction Description";
    public final String RECURRENCE_LABEL = "Recurrence";

    private float transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String recurrence;

    public PeriodicTransactionViewModel() {
        super("Periodic Transaction");
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }
}

