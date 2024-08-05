package use_case.transaction.one_time;

import entity.account.SharedAccount;
import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.TransactionOutputData;

/**
 * The SharedAccountOneTimeTransactionOutputData class represents the output data of a one-time transaction operation
 * that involves a shared account.
 * It includes details such as the new balance of the shared account, transaction amount, date, description, category, and status of the use case.
 *
 */
public class SharedAccountOneTimeTransactionOutputData extends OneTimeTransactionOutputData {
    private float newSharedAccountBalance;

    /**
     * Constructs a SharedAccountOneTimeTransactionOutputData object with the specified details.
     *
     * @param oneTimeTransaction the one-time transaction entity
     * @param newSharedAccountBalance the new balance of the shared account
     */
    public SharedAccountOneTimeTransactionOutputData(OneTimeTransaction oneTimeTransaction, float newSharedAccountBalance) {
        super(oneTimeTransaction);
        this.newSharedAccountBalance = newSharedAccountBalance;
    }

    /**
     * Gets the new balance of the shared account.
     *
     * @return the new balance of the shared account
     */
    public float getNewSharedAccountBalance() {
        return newSharedAccountBalance;
    }
}