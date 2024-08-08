package use_case.signup;

import com.google.api.services.drive.model.User;
import data_access.authentication.AccountSignupDataAccessInterface;
import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.AccountFactory;

/**
 * Abstract base class for account sign-up interactors.
 */
public abstract class SignupInteractor<
        T extends AccountSignupDataAccessInterface,
        I extends SignupInputData> {
    protected final AccountFactory accountFactory;
    protected final T userDataAccessObject;

    public SignupInteractor(T userSignupDataAccessInterface,
                                   AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
        this.userDataAccessObject = userSignupDataAccessInterface;
    }

    public abstract void execute(I signupInputData) throws Exception;
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