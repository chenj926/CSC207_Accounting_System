package use_case.signup;

import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.AccountFactory;

/**
 * Abstract base class for account sign-up interactors.
 */
public abstract class SignupInteractor {
    protected final AccountFactory accountFactory;
    protected final UserSignupDataAccessInterface userDataAccessObject;

    public SignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                                   AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
        this.userDataAccessObject = userSignupDataAccessInterface;
    }

    /**
     * Validates if the provided password is valid (not null or empty).
     *
     * @param password the password to check
     * @return true if the password is valid, false otherwise
     */
    protected boolean checkPassword(String password) {
        return password != null && !password.isEmpty();
    }

    /**
     * Validates if the provided identification is valid (not null or empty).
     *
     * @param id the identification to check
     * @return true if the identification is valid, false otherwise
     */
    protected boolean checkIdentification(String id) {
        return id != null && !id.isEmpty();
    }
}