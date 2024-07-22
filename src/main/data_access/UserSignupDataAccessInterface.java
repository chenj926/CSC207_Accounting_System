package data_access;

import entity.UserAccount;

/**
 * Interface for data access operations related to user account signup.
 * <p>
 * This interface provides methods to check the existence of a user by their identification
 * and to save a new user account into the data store.
 * </p>
 *
 * @author Eric
 */
public interface UserSignupDataAccessInterface {

    /**
     * Checks whether a user account with the specified identification already exists.
     *
     * @param identification the unique identifier for the user account
     * @return true if a user account with the specified identification exists, false otherwise
     */
    boolean existById(String identification);

    /**
     * Saves a new user account to the data store.
     *
     * @param user the user account to be saved
     */
    void save(UserAccount user);
}