package use_case;

public class PeriodicTransactionInputData {

    private float transactionAmount;
    private final String identification;
    private String transactionStartDate;
    private String transactionEndDate;
    private String transactionDuration;
    private String transactionDescription;


    public PeriodicTransactionInputData(String identification, float transactionAmount, String transactionStartDate,
                                        String transactionDescription, String transactionDuration, String transactionEndDate) {
        this.transactionAmount = transactionAmount;
        this.transactionStartDate = transactionStartDate;
        this.transactionDescription = transactionDescription;
        this.transactionEndDate = transactionEndDate;
        this.identification = identification;
        this.transactionDuration = transactionDuration;

    }

    public String getIdentification() {
        return this.identification;
    }

    public float getTransactionAmount() {
        return this.transactionAmount;
    }

    public String getTransactionStartDate() {
        return this.transactionStartDate;
    }

    public String getTransactionDescription() {
        return this.transactionDescription;
    }

    public String getTransactionEndDate() {
        return this.transactionEndDate;
    }

    public String getTransactionDuration() {
        return this.transactionDuration;
    }

}