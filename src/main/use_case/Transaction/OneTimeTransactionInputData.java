package use_case.Transaction;

/**
 * The OneTimeTransactionInputData class represents the input data required for a one-time transaction operation.
 * It includes details such as the transaction amount, date, description, and category.
 *
 * @author Dana
 * @author Eric
 */
public class OneTimeTransactionInputData {

    private String transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String transactionCategory;

    /**
     * Constructs a OneTimeTransactionInputData object with the specified details.
     *
     * @param transactionAmount      the amount of the transaction
     * @param transactionDate        the date of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     */
    public OneTimeTransactionInputData(String transactionAmount, String transactionDate, String transactionDescription,
                                       String transactionCategory) {
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return the amount of the transaction
     */
    public String getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return the description of the transaction
     */
    public String getTransactionDescription() {
        return transactionDescription;
    }

    /**
     * Gets the category of the transaction.
     *
     * @return the category of the transaction
     */
    public String getTransactionCategory() {
        return transactionCategory;
    }
}