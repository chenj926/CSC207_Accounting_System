package use_case.transaction.one_time;

import use_case.transaction.TransactionInputData;

/**
 * The OneTimeTransactionInputData class represents the input data required for a one-time transaction operation.
 * It includes details such as the transaction amount, date, description, and category.
 *
 * @author Dana
 * @author Eric
 */
public class OneTimeTransactionInputData extends TransactionInputData {
    private String transactionDate;

    /**
     * Constructs a OneTimeTransactionInputData object with the specified details.
     *
     * @param transactionAmount      the amount of the transaction
     * @param transactionDate        the date of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     */
    public OneTimeTransactionInputData(String id, String transactionAmount, String transactionDate,
                                       String transactionDescription, String transactionCategory) {
        super(id, transactionAmount, transactionDescription, transactionCategory);
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    public String getTransactionDate() {
        return transactionDate;
    }
}