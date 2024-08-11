package data_access.account.shared_account;

import data_access.authentication.shared_account.SharedAccountSignupDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import entity.transaction.Transaction;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory data access object (DAO) for shared account operations.
 * <p>
 * This class implements the {@link SharedAccountDataAccessInterface} interface and provides an in-memory
 * implementation for managing shared accounts. It is intended for testing purposes and does not persist data
 * beyond the lifetime of the application.
 * </p>
 *
 * @author Eric
 * @author Jessica
 */

// in memory DAO for test purposes
public class InMemorySharedAccountDataAccessObject implements SharedAccountSignupDataAccessInterface, SharedAccountDataAccessInterface {
    private final Map<String, SharedAccount> shareAcc = new HashMap<>();



//    @Override
//    public boolean login(SharedAccount sharedAccount) {
//        return shareAcc.containsKey(sharedAccount.getUsername());
//    }
//
//    @Override
//    public void saveTransaction(SharedAccountOneTimeTransactionOutputData outputData, SharedAccountPeriodicTransactionOutputData sharedPeriodicOutputData, boolean isPeriodic) {
//
//    }


    /**
     * Checks if a shared account exists with the given identification.
     *
     * @param sharedAccountId the unique identifier for the shared account
     * @return {@code true} if a shared account exists with the specified identification; {@code false} otherwise
     */
    @Override
    public boolean existById(String sharedAccountId) {
        return shareAcc.containsKey(sharedAccountId);
    }

    /**
     * Saves a new shared account.
     *
     * @param newSharedAcc the {@link SharedAccount} to be saved
     */
    public void save(SharedAccount newSharedAcc) {
        shareAcc.put(newSharedAcc.getIdentification(), newSharedAcc);
    }


    public void saveTransaction(SharedAccountOneTimeTransactionOutputData oneTimeOutputData, SharedAccountPeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {

    }


    public List<Transaction> readTransactions(String userId) {
        return List.of();
    }

    /**
     * Deletes a shared account by its unique identification.
     *
     * @param sharedAccountId the unique identifier for the shared account to be deleted
     */
    public void deleteById(String sharedAccountId) {
        shareAcc.remove(sharedAccountId);
    }


    /**
     * Retrieves a shared account by its unique identification.
     *
     * @param sharedAccountId the unique identifier for the shared account
     * @return the {@link SharedAccount} associated with the specified identification, or {@code null} if not found
     */
    public SharedAccount getById(String sharedAccountId) {
        return shareAcc.get(sharedAccountId);
    }



    /**
     * Retrieves all shared accounts.
     *
     * @return a {@link Map} of all shared accounts where the key is the shared account's identification and the value is the {@link SharedAccount}
     */
    public Map<String, SharedAccount> getAllShareAcc() {
        return shareAcc;
    }


    public void update(SharedAccount sharedAccount) {
        // Check if the account exists before updating
        if (shareAcc.containsKey(sharedAccount.getIdentification())) {
            shareAcc.put(sharedAccount.getIdentification(), sharedAccount);
        } else {
            // Handle the case where the account doesn't exist, if needed
            throw new IllegalArgumentException("Shared account does not exist: " + sharedAccount.getIdentification());
        }
    }
}