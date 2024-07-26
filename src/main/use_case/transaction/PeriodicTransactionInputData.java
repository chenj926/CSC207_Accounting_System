package use_case.transaction;

/**
 * The PeriodicTransactionInputData class represents the input data required for a periodic transaction operation.
 * It includes details such as the transaction amount, start date, end date, period, and description.
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
public class PeriodicTransactionInputData {

    private String transactionAmount;
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