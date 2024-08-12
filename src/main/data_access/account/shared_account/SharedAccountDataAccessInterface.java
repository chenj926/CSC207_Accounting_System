package data_access.account.shared_account;

import data_access.account.AccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;

/**
 * Interface for data access operations related to shared accounts.
 * <p>
 * This interface provides methods to manage shared accounts, including checking
 * existence and saving shared accounts.
 * </p>
 *
 * @author Eric
 */
public interface SharedAccountDataAccessInterface
        extends AccountDataAccessInterface<SharedAccount, SharedAccountOneTimeTransactionOutputData, SharedAccountPeriodicTransactionOutputData> {

//    /**
//     * Checks whether a shared account with the specified identification exists.
//     *
//     * @param identification the unique identifier for the shared account
//     * @return true if a shared account with the specified identification exists, false otherwise
//     */
//    boolean existById(String identification);
//
//    /**
//     * Saves a new shared account to the data store.
//     *
//     * @param sharedAccount the shared account to be saved
//     */
//    void save(SharedAccount sharedAccount);
//
//    /**
//     * Retrieves a shared account by its unique identification.
//     *
//     * @param identification the unique identifier for the shared account
//     * @return the shared account associated with the specified identification, or null if not found
//     */
//    SharedAccount getById(String identification);
//
//    /**
//     * Updates an existing shared account in the data store.
//     *
//     * @param sharedAccount the shared account to be updated
//     */
//    void update(SharedAccount sharedAccount);
//
//    /**
//     * Deletes a shared account by its unique identification.
//     *
//     * @param identification the unique identifier for the shared account to be deleted
//     */
//    void deleteById(String identification);
//
//
//    void saveTransaction(UserAccountOneTimeTransactionOutputData outputData,
//                         UserAccountPeriodicTransactionOutputData sharedPeriodicOutputData,
//                         boolean isPeriodic);
}