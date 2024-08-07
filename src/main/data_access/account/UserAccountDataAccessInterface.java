package data_access.account;

import entity.account.UserAccount;
import entity.transaction.Transaction;
import org.checkerframework.checker.units.qual.A;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.util.List;

/**
 * Interface for data access operations related to user accounts.
 * <p>
 * This interface provides methods to manage user accounts and transactions, including
 * checking existence, retrieving, updating, deleting, and saving user accounts,
 * as well as saving transaction data.
 * </p>
 *
 * @author Eric
 * @author Jessica
 * @author Dana
 */
public interface UserAccountDataAccessInterface
        extends AccountDataAccessInterface<UserAccount, OneTimeTransactionOutputData, PeriodicTransactionOutputData> {
//    /**
//     * Retrieves a user account by its identification.
//     *
//     * @param identification the unique identifier for the user account
//     * @return the user account with the specified identification
//     */
//    boolean existById(String identification);
//
//    /**
//     * Retrieves a user account by its identification.
//     *
//     * @param identification the unique identifier for the user account
//     * @return the user account with the specified identification
//     */
//    UserAccount getById(String identification);
//
//    /**
//     * Updates an existing user account in the data store.
//     *
//     * @param userAccount the user account with updated information
//     */
//    void update(UserAccount userAccount);
//
//    /**
//     * Deletes a user account from the data store by its identification.
//     *
//     * @param identifier the unique identifier for the user account to be deleted
//     */
//    void deleteById(String identifier);
//
//    /**
//     * Saves a new user account to the data store.
//     *
//     * @param userAccount the user account to be saved
//     */
//    void save(UserAccount userAccount);
//
//    /**
//     * Saves transaction data to the data store.
//     *
//     * @param oneTimeOutputData the output data for one-time transactions
//     * @param periodicOutputData the output data for periodic transactions
//     * @param isPeriodic true if the transaction is periodic, false if it is one-time
//     */
//    void saveTransaction(OneTimeTransactionOutputData oneTimeOutputData,
//                         PeriodicTransactionOutputData periodicOutputData, boolean isPeriodic);
//
//    List<Transaction> readTransactions(String userId);

    // void updateId(UserAccount userAccount);
    //account balance
//    void updateBalance(float balance);
//    //account inflow
//    void updateInflow(float inFlow);
//    //account outflow
//    void updateOutflow(float outFlow);

}
