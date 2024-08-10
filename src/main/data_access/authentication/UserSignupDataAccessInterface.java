package data_access.authentication;

import data_access.account.AccountDataAccessInterface;
import entity.account.UserAccount;

/**
 * Interface for data access operations related to user account signup.
 * <p>
 * This interface provides methods to check the existence of a user by their identification
 * and to save a new user account into the data store.
 * </p>
 *
 * @author Eric
 */
public interface UserSignupDataAccessInterface extends AccountSignupDataAccessInterface<UserAccount> {

}