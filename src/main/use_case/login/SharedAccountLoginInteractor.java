package use_case.login;

import data_access.authentication.SharedAccountLoginDataAccessInterface;
import entity.account.UserAccount;
import entity.account.SharedAccount;
import data_access.authentication.LoginDataAccessInterface;
import data_access.authentication.SharedAccountLoginDataAccessInterface;
import data_access.account.ShareAccountDataAccessInterface;
import use_case.signup.SharedAccountSignupOutputBoundary;
import use_case.signup.SharedAccountSignupOutputData;

/**
 * The SharedAccountLoginInteractor class extends the LoginInteractor to handle login for shared accounts.
 * It includes additional validation for the shared account ID and ensures that the user is part of the shared account.
 */
public class SharedAccountLoginInteractor extends LoginInteractor implements SharedAccountLoginInputBoundary{
    final SharedAccountLoginDataAccessInterface sharedAccountLoginDataAccessObject;
    final SharedAccountLoginOutputBoundary sharedPresenter;

    /**
     * Constructs a SharedAccountLoginInteractor object with the specified data access interfaces and output boundary.
     *
     * @param sharedAccountLoginDataAccessInterface      the data access interface for user data
     * @param sharedLoginOutputBoundary           the output boundary for presenting the login results
     */
    public SharedAccountLoginInteractor(SharedAccountLoginDataAccessInterface sharedAccountLoginDataAccessInterface,
                                        SharedAccountLoginOutputBoundary sharedLoginOutputBoundary) {
        super(sharedAccountLoginDataAccessInterface, sharedLoginOutputBoundary);
        this.sharedPresenter = sharedLoginOutputBoundary;
        this.sharedAccountLoginDataAccessObject = sharedAccountLoginDataAccessInterface;
    }

    /**
     * Executes the shared account login process with the given input data.
     *
     * @param sharedLoginInputData the input data required for the shared account login process
     */
    @Override
    public void execute(SharedAccountLoginInputData sharedLoginInputData) {

        boolean validPassword = this.checkPassword(sharedLoginInputData.getPassword());
        boolean validIdentification = this.checkIdentification(sharedLoginInputData.getIdentification());
        boolean validSharedAccountId = this.checkIdentification(sharedLoginInputData.getSharedAccountId());

        if (!validPassword || !validIdentification || !validSharedAccountId) {
            presenter.prepareFailView("Identification, Password, and Shared Account ID cannot be empty!");
            return;
        }

        if (!userDataAccessObject.existById(sharedLoginInputData.getIdentification())) {
            presenter.prepareFailView("User not found");
            return;
        }

        UserAccount userAccount = userDataAccessObject.getById(sharedLoginInputData.getIdentification());
        if (userAccount == null || !userAccount.getPassword().equals(sharedLoginInputData.getPassword())) {
            presenter.prepareFailView("Incorrect Password!");
            return;
        }

        if (!sharedAccountLoginDataAccessObject.existById(sharedLoginInputData.getSharedAccountId())) {
            presenter.prepareFailView("Shared account not found");
            return;
        }

        SharedAccount sharedAccount = sharedAccountLoginDataAccessObject.getById(sharedLoginInputData.getSharedAccountId());
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

