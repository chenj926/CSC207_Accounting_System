package use_case;

public class OneTimeTransactionInputData {

    private float transactionAmount;
    private String TransactionDate;
    private String TransactionDescription;
    private String TransactionCategory;
    private String TransactionNotes;

    public OneTimeTransactionInputData(float transactionAmount, String transactionDate, String transactionDescription,
                                       String transactionCategory, String transactionNotes) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;
        this.TransactionNotes = TransactionNotes;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionDate() {
        return TransactionDate
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public String getTransactionNotes() {
        return TransactionNotes;
    }

    }
}