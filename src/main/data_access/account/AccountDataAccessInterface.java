package data_access.account;

import entity.account.Account;
import entity.transaction.Transaction;
import use_case.transaction.TransactionOutputData;
import use_case.transaction.one_time.OneTimeTransactionOutputData;

import java.util.List;

public interface AccountDataAccessInterface<
        A extends Account,
        O extends OneTimeTransactionOutputData,
        P extends TransactionOutputData> {
    /**
     * Retrieves a user account by its identification.
     *
     * @param identification the unique identifier for the user account
     * @return the user account with the specified identification
     */
    boolean existById(String identification);

    /**
     * Retrieves a user account by its identification.
     *
     * @param identification the unique identifier for the user account
     * @return the user account with the specified identification
     */
    A getById(String identification);

    /**
     * Updates an existing user account in the data store.
     *
     * @param account the user account with updated information
     */
    void update(A account);

    /**
     * Deletes a user account from the data store by its identification.
     *
     * @param identifier the unique identifier for the user account to be deleted
     */
    void deleteById(String identifier);

    /**
     * Saves a new user account to the data store.
     *
     * @param account the user account to be saved
     */
    void save(A account);

    /**
     * Saves transaction data to the data store.
     *
     * @param oneTimeOutputData the output data for one-time transactions
     * @param periodicOutputData the output data for periodic transactions
     * @param isPeriodic true if the transaction is periodic, false if it is one-time
     */
    void saveTransaction(O oneTimeOutputData,
                         P periodicOutputData,
                         boolean isPeriodic);

    List<Transaction> readTransactions(String userId);
}
