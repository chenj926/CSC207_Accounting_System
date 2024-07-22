package data_access;

import data_access.ShareAccountDataAccessInterface;
import entity.SharedAccount;

import java.util.*;

/**
 * In-memory data access object (DAO) for shared account operations.
 * <p>
 * This class implements the {@link ShareAccountDataAccessInterface} interface and provides an in-memory
 * implementation for managing shared accounts. It is intended for testing purposes and does not persist data
 * beyond the lifetime of the application.
 * </p>
 *
 * @author Eric
 * @author Jessica
 */

// in memory DAO for test purposes
public class InMemoryShareAccountDataAccessObject implements ShareAccountDataAccessInterface{
    private final Map<String, SharedAccount> shareAcc = new HashMap<>();

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
}