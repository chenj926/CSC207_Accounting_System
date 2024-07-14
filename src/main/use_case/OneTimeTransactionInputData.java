package use_case;

public class OneTimeTransactionInputData {

    private float transactionAmount;
    private final String identification;
    private String transactionDate;
    private String transactionDescription;
    private String transactionCategory;
    private String transactionNotes;

    public OneTimeTransactionInputData(float transactionAmount, String identification,String transactionDate, String transactionDescription,
                                       String transactionCategory, String transactionNotes) {
        this.transactionAmount = transactionAmount;
        this.identification = identification;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;
        this.transactionNotes = transactionNotes;
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

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public String getTransactionNotes() {
        return transactionNotes;
    }
}