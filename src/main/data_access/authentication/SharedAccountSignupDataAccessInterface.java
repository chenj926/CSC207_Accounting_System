package data_access.authentication;

import data_access.account.AccountDataAccessInterface;
import entity.account.SharedAccount;

/**
 * Interface for data access operations related to shared account signup.
 * <p>
 * This interface provides methods to check the existence of a user by their identification
 * and to save a new shared account into the data store.
 * </p>
 *
 * @author Jessica
 */
public interface SharedAccountSignupDataAccessInterface extends AccountSignupDataAccessInterface<SharedAccount> {

}