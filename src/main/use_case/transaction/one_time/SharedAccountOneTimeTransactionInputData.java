package use_case.transaction.one_time;

import use_case.transaction.TransactionInputData;

/**
 * The SharedAccountOneTimeTransactionInputData class represents the input data required for a one-time transaction operation
 * that involves a shared account.
 * It includes details such as the transaction amount, date, description, category, and the shared account identifier.
 *
 */
public class SharedAccountOneTimeTransactionInputData extends OneTimeTransactionInputData {
    private String sharedAccountId;

    /**
     * Constructs a SharedAccountOneTimeTransactionInputData object with the specified details.
     *
     * @param transactionAmount      the amount of the transaction
     * @param transactionDate        the date of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     * @param sharedAccountId        the identifier of the shared account
     */
    public SharedAccountOneTimeTransactionInputData(String transactionAmount, String transactionDate, String transactionDescription,
                                                    String transactionCategory, String sharedAccountId) {
        super(transactionAmount, transactionDate, transactionDescription, transactionCategory);
        this.sharedAccountId = sharedAccountId;
    }

    /**
     * Gets the identifier of the shared account.
     *
     * @return the identifier of the shared account
     */
    public String getSharedAccountId() {
        return sharedAccountId;
    }
}