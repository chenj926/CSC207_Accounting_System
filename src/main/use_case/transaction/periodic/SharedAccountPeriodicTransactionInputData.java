package use_case.transaction.periodic;

import entity.account.SharedAccount;
import use_case.transaction.TransactionInputData;

/**
 * The SharedAccountPeriodicTransactionInputData class represents the input data required for a periodic transaction operation
 * involving a shared account.
 * It includes details such as the shared account, transaction amount, start date, end date, period, and description.
 */
public class SharedAccountPeriodicTransactionInputData extends PeriodicTransactionInputData {
    private SharedAccount sharedAccount;

    /**
     * Constructs a SharedAccountPeriodicTransactionInputData object with the specified details.
     *
     * @param sharedAccount          the shared account for the transaction
     * @param transactionAmount      the amount of the transaction
     * @param transactionStartDate   the start date of the transaction
     * @param transactionEndDate     the end date of the transaction
     * @param transactionPeriod      the period of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     */
    public SharedAccountPeriodicTransactionInputData(SharedAccount sharedAccount, String transactionAmount,
                                                     String transactionStartDate, String transactionEndDate,
                                                     String transactionPeriod, String transactionDescription,
                                                     String transactionCategory) {
        super(transactionAmount, transactionStartDate, transactionDescription, transactionPeriod, transactionEndDate, transactionCategory);
        this.sharedAccount = sharedAccount;
    }

    /**
     * Gets the shared account for the transaction.
     *
     * @return the shared account for the transaction
     */
    public SharedAccount getSharedAccount() {
        return this.sharedAccount;
    }
}