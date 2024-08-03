package use_case.login;

import entity.account.UserAccount;
import entity.account.SharedAccount;
import data_access.authentication.LoginDataAccessInterface;
import data_access.account.ShareAccountDataAccessInterface;

/**
 * The SharedAccountLoginInteractor class extends the LoginInteractor to handle login for shared accounts.
 * It includes additional validation for the shared account ID and ensures that the user is part of the shared account.
 */
public class SharedAccountLoginInteractor extends LoginInteractor {
    final ShareAccountDataAccessInterface sharedAccountDataAccessObject;

    /**
     * Constructs a SharedAccountLoginInteractor object with the specified data access interfaces and output boundary.
     *
     * @param loginDataAccessInterface      the data access interface for user data
     * @param sharedAccountDataAccessObject the data access interface for shared account data
     * @param loginOutputBoundary           the output boundary for presenting the login results
     */
    public SharedAccountLoginInteractor(LoginDataAccessInterface loginDataAccessInterface,
                                        ShareAccountDataAccessInterface sharedAccountDataAccessObject,
                                        LoginOutputBoundary loginOutputBoundary) {
        super(loginDataAccessInterface, loginOutputBoundary);
        this.sharedAccountDataAccessObject = sharedAccountDataAccessObject;
    }

    /**
     * Executes the shared account login process with the given input data.
     *
     * @param loginInputData the input data required for the shared account login process
     */
    @Override
    public void execute(LoginInputData loginInputData) {
        if (!(loginInputData instanceof SharedAccountLoginInputData)) {
            throw new IllegalArgumentException("Invalid input data type for SharedAccountLoginInteractor");
        }

        SharedAccountLoginInputData sharedLoginData = (SharedAccountLoginInputData) loginInputData;

        boolean validPassword = this.checkPassword(sharedLoginData.getPassword());
        boolean validIdentification = this.checkIdentification(sharedLoginData.getIdentification());
        boolean validSharedAccountId = this.checkIdentification(sharedLoginData.getSharedAccountId());

        if (!validPassword || !validIdentification || !validSharedAccountId) {
            presenter.prepareFailView("Identification, Password, and Shared Account ID cannot be empty!");
            return;
        }

        if (!userDataAccessObject.existById(sharedLoginData.getIdentification())) {
            presenter.prepareFailView("User not found");
            return;
        }

        UserAccount userAccount = userDataAccessObject.getById(sharedLoginData.getIdentification());
        if (userAccount == null || !userAccount.getPassword().equals(sharedLoginData.getPassword())) {
            presenter.prepareFailView("Incorrect Password!");
            return;
        }

        if (!sharedAccountDataAccessObject.existById(sharedLoginData.getSharedAccountId())) {
            presenter.prepareFailView("Shared account not found");
            return;
        }

        SharedAccount sharedAccount = sharedAccountDataAccessObject.getById(sharedLoginData.getSharedAccountId());
        if (!sharedAccount.getSharedUserIdentifications().contains(userAccount.getIdentification())) {
            presenter.prepareFailView("User account and shared account do not match");
            return;
        }

        boolean isLogin = userDataAccessObject.login(userAccount);

        if (!isLogin) {
            presenter.prepareFailView("Incorrect Password!");
        } else {
            LoginOutputData loginOutputData = new LoginOutputData(userAccount.getIdentification(), true);
            presenter.prepareSuccessView(loginOutputData);
        }
    }
}

