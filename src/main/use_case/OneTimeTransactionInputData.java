package use_case;

public class OneTimeTransactionInputData {

    private float transactionAmount;
    private final String identification;
    private String transactionDate;
    private String transactionDescription;
    private String transactionCategory;

    public OneTimeTransactionInputData(float transactionAmount, String identification,String transactionDate, String transactionDescription,
                                       String transactionCategory) {
        this.transactionAmount = transactionAmount;
        this.identification = identification;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;
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

}