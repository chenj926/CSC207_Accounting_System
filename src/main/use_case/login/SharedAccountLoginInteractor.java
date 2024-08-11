package use_case.login;

import data_access.authentication.SharedAccountLoginDataAccessInterface;
import entity.account.SharedAccount;
;
import use_case.update_periodic_at_login.UserAccountUpdatePeriodicAtLoginInputData;

import java.time.LocalDate;

/**
 * The SharedAccountLoginInteractor class extends the LoginInteractor to handle login for shared accounts.
 * It includes additional validation for the shared account ID and ensures that the user is part of the shared account.
 *
 * @author Xile Chen, Eric Chen
 */
public class SharedAccountLoginInteractor extends LoginInteractor<
        SharedAccountLoginDataAccessInterface,
        SharedAccountLoginOutputBoundary,
        SharedAccountLoginOutputData,
        SharedAccountLoginInputData>implements SharedAccountLoginInputBoundary{

    /**
     * Constructs a SharedAccountLoginInteractor object with the specified data access interfaces and output boundary.
     *
     * @param sharedAccountLoginDataAccessInterface      the data access interface for user data
     * @param sharedLoginOutputBoundary           the output boundary for presenting the login results
     */
    public SharedAccountLoginInteractor(SharedAccountLoginDataAccessInterface sharedAccountLoginDataAccessInterface,
                                        SharedAccountLoginOutputBoundary sharedLoginOutputBoundary) {
        super(sharedAccountLoginDataAccessInterface, sharedLoginOutputBoundary);
    }

    /**
     * Executes the shared account login process with the given input data.
     *
     * @param sharedLoginInputData the input data required for the shared account login process
     */
    @Override
    public void execute(SharedAccountLoginInputData sharedLoginInputData) {

        boolean validSharedAccountId = this.checkIdentification(sharedLoginInputData.getIdentification());
        boolean validSharedAccountPassword = this.checkPassword(sharedLoginInputData.getPassword());

        if (!validSharedAccountPassword && !validSharedAccountId) {
            this.presenter.prepareFailView("Identification AND Password can not be empty!");
            return;
        } else if (!validSharedAccountPassword) {
            this.presenter.prepareFailView("Password can not be empty!");
            return;
        } else if (!validSharedAccountId) {
            this.presenter.prepareFailView("Identification can not be empty!");
            return;
        } else {
            if (!this.userDataAccessObject.existById(sharedLoginInputData.getIdentification())) {
                this.presenter.prepareFailView("Shared Account not found");
                return;
            }

            SharedAccount sharedAccount = this.userDataAccessObject.getById(sharedLoginInputData.getIdentification());
            if (sharedAccount != null && !sharedAccount.getPassword().equals(sharedLoginInputData.getPassword())) {
                this.presenter.prepareFailView("Incorrect Password!");
                return;
            }

            boolean isLogin = this.userDataAccessObject.login(sharedAccount);

            if (!isLogin) {
                this.presenter.prepareFailView("Incorrect Account Password!");
            } else {
                SharedAccountLoginOutputData sharedLoginOutputData = new SharedAccountLoginOutputData(sharedLoginInputData.getIdentification(), true);
                this.presenter.prepareSuccessView(sharedLoginOutputData);

                // Notify mediator on successful login
                UserAccountUpdatePeriodicAtLoginInputData userAccountUpdatePeriodicAtLoginInputData = new UserAccountUpdatePeriodicAtLoginInputData(sharedLoginInputData.getIdentification(), LocalDate.now());
                mediator.notifyLoginResult(true, userAccountUpdatePeriodicAtLoginInputData);
            }
        }
    }

}

