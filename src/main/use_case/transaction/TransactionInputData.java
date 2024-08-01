package use_case.transaction;

import java.time.LocalDate;

public abstract class TransactionInputData {
    protected String transactionAmount;
    protected String transactionDescription;
    protected String transactionCategory;

    /**
     * Co   nstructs a TransactionInputData object with the specified details.
     *
     * @param transactionAmount      the amount of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     */
    public TransactionInputData(String transactionAmount,
                                String transactionDescription,
                                String transactionCategory) {
        this.transactionAmount = transactionAmount;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;

    }

    /**
     * Gets the transaction amount.
     *
     * @return the transaction amount
     */
    public String getTransactionAmount() {
        return this.transactionAmount;
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
     * Gets the transaction category.
     *
     * @return the transaction category
     */
    public String getTransactionCategory() {
        return this.transactionCategory;
    }
}
