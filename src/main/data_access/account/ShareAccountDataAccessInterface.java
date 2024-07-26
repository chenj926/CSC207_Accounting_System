package data_access.account;

import entity.account.SharedAccount;

/**
 * Interface for data access operations related to shared accounts.
 * <p>
 * This interface provides methods to manage shared accounts, including checking
 * existence and saving shared accounts.
 * </p>
 *
 * @author Eric
 */
public interface ShareAccountDataAccessInterface {

    /**
     * Checks whether a shared account with the specified identification exists.
     *
     * @param identification the unique identifier for the shared account
     * @return true if a shared account with the specified identification exists, false otherwise
     */
    boolean existById(String identification);

    /**
     * Saves a new shared account to the data store.
     *
     * @param sharedAccount the shared account to be saved
     */
    void save(SharedAccount sharedAccount);

}