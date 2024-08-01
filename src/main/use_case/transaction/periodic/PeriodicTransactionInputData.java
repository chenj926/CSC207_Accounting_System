package use_case.transaction.periodic;

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
    private String transactionCategory;


    /**
     * Constructs a OneTimeTransactionInputData object with the specified details.
     *
     * @param transactionAmount      the amount of the transaction
     * @param transactionStartDate        the date of the transaction
     * @param transactionEndDate        the date of the transaction
     * @param transactionPeriod      the period of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     */
    public PeriodicTransactionInputData(String transactionAmount, String transactionStartDate,
                                        String transactionDescription, String transactionPeriod,
                                        String transactionEndDate, String transactionCategory) {
        this.transactionAmount = transactionAmount;
        this.transactionStartDate = transactionStartDate;
        this.transactionDescription = transactionDescription;
        this.transactionEndDate = transactionEndDate;
        this.transactionPeriod = transactionPeriod;
        this.transactionCategory = transactionCategory;

    }

    /**
     * Gets the amount of the transaction.
     *
     * @return the amount of the transaction
     */
    public String getTransactionAmount() {
        return this.transactionAmount;
    }

    /**
     * Gets the start date of the transaction.
     *
     * @return the date of the transaction
     */
    public String getTransactionStartDate() {
        return this.transactionStartDate;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return the description of the transaction
     */
    public String getTransactionDescription() {
        return this.transactionDescription;
    }

    /**
     * Gets the end date of the transaction.
     *
     * @return the end date of the transaction
     */
    public String getTransactionEndDate() {
        return this.transactionEndDate;
    }

    /**
     * Gets the period(frequency) of the transaction.
     *
     * @return the period of the transaction
     */
    public String getTransactionPeriod() {
        return this.transactionPeriod;
    }

    /**
     * Gets the category of the transaction.
     *
     * @return the category of the transaction
     */
    public String getTransactionCategory() {
        return this.transactionCategory;
    }

}