package use_case;

public class PeriodicTransactionInputData {

    private String transactionAmount;
//    private final String identification;
    private String transactionStartDate;
    private String transactionEndDate;
    private String transactionPeriod;
    private String transactionDescription;


    public PeriodicTransactionInputData(String transactionAmount, String transactionStartDate,
                                        String transactionDescription, String transactionPeriod, String transactionEndDate) {
        this.transactionAmount = transactionAmount;
        this.transactionStartDate = transactionStartDate;
        this.transactionDescription = transactionDescription;
        this.transactionEndDate = transactionEndDate;
        this.transactionPeriod = transactionPeriod;

    }


    public String getTransactionAmount() {
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

    public String getTransactionPeriod() {
        return this.transactionPeriod;
    }

}