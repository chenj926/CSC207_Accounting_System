package use_case.login;

import data_access.authentication.SharedAccountLoginDataAccessInterface;
import entity.account.UserAccount;
import entity.account.SharedAccount;
import data_access.authentication.LoginDataAccessInterface;;
import data_access.account.ShareAccountDataAccessInterface;
import use_case.signup.SharedAccountSignupOutputBoundary;
import use_case.signup.SharedAccountSignupOutputData;
import use_case.update_periodic_at_login.UpdatePeriodicAtLoginInputData;

import java.time.LocalDate;

/**
 * The SharedAccountLoginInteractor class extends the LoginInteractor to handle login for shared accounts.
 * It includes additional validation for the shared account ID and ensures that the user is part of the shared account.
 *
 * @author Xile Chen, Eric Chen
 */
public class SharedAccountLoginInteractor implements SharedAccountLoginInputBoundary{
    final SharedAccountLoginDataAccessInterface sharedAccountLoginDataAccessObject;
    final SharedAccountLoginOutputBoundary sharedPresenter;
    private LoginMediator mediator;

    /**
     * Constructs a SharedAccountLoginInteractor object with the specified data access interfaces and output boundary.
     *
     * @param sharedAccountLoginDataAccessInterface      the data access interface for user data
     * @param sharedLoginOutputBoundary           the output boundary for presenting the login results
     */
    public SharedAccountLoginInteractor(SharedAccountLoginDataAccessInterface sharedAccountLoginDataAccessInterface,
                                        SharedAccountLoginOutputBoundary sharedLoginOutputBoundary) {
        this.sharedPresenter = sharedLoginOutputBoundary;
        this.sharedAccountLoginDataAccessObject = sharedAccountLoginDataAccessInterface;
    }

    /**
     * Sets the mediator for the interactor.
     *
     * @param mediator the LoginMediator instance to set
     */
    public void setMediator(LoginMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * Executes the shared account login process with the given input data.
     *
     * @param sharedLoginInputData the input data required for the shared account login process
     */
    @Override
    public void execute(SharedAccountLoginInputData sharedLoginInputData) {

        boolean validSharedAccountId = this.checkIdentification(sharedLoginInputData.getSharedAccountId());
        boolean validSharedAccountPassword = this.checkPassword(sharedLoginInputData.getSharedPassword());

        if (!validSharedAccountPassword && !validSharedAccountId) {
            sharedPresenter.prepareFailView("Identification AND Password can not be empty!");
            return;
        } else if (!validSharedAccountPassword) {
            sharedPresenter.prepareFailView("Password can not be empty!");
            return;
        } else if (!validSharedAccountId) {
            sharedPresenter.prepareFailView("Identification can not be empty!");
            return;
        } else {
            if (!sharedAccountLoginDataAccessObject.existById(sharedLoginInputData.getSharedAccountId())) {
                sharedPresenter.prepareFailView("Shared Account not found");
                return;
            }

            SharedAccount sharedAccount = sharedAccountLoginDataAccessObject.getById(sharedLoginInputData.getSharedAccountId());
            if (sharedAccount != null && !sharedAccount.getPassword().equals(sharedLoginInputData.getSharedPassword())) {
                sharedPresenter.prepareFailView("Incorrect Password!");
                return;
            }

            boolean isLogin = sharedAccountLoginDataAccessObject.login(sharedAccount);

            if (!isLogin) {
                sharedPresenter.prepareFailView("Incorrect Account Password!");
            } else {
                SharedAccountLoginOutputData sharedLoginOutputData = new SharedAccountLoginOutputData(sharedLoginInputData.getSharedAccountId(), true);
                sharedPresenter.prepareSuccessView(sharedLoginOutputData);

                // Notify mediator on successful login
                UpdatePeriodicAtLoginInputData updatePeriodicAtLoginInputData = new UpdatePeriodicAtLoginInputData(sharedLoginInputData.getSharedAccountId(), LocalDate.now());
                mediator.notifyLoginResult(true, updatePeriodicAtLoginInputData);
            }
        }
    }

    /**
     * Checks if the provided password is valid (not null or empty).
     *
     * @param password the password to check
     * @return true if the password is valid, false otherwise
     */
    public boolean checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the provided identification is valid (not null or empty).
     *
     * @param id the identification to check
     * @return true if the identification is valid, false otherwise
     */
    public boolean checkIdentification(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return true;
    }

}

