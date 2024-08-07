package use_case.login;

import data_access.account.UserAccountDataAccessInterface;
import use_case.update_periodic_at_login.UpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.UpdatePeriodicAtLoginInputData;

/**
 * The LoginMediator class mediates the interaction between the login process and periodic transaction updates.
 * It orchestrates actions post-login, like updating periodic transactions.
 *
 * @author Jessica
 */
public class UserLoginMediator {
    private final UserLoginInputBoundary loginInteractor;
    private final SharedAccountLoginInputBoundary sharedLoginInteractor;
    private final UpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor;
    private final UserAccountDataAccessInterface periodicTransactionDataAccessObject;

    /**
     * Constructs a LoginMediator object with the specified interactors.
     *
     * @param loginInteractor the login interactor
     * @param periodicTransactionDataAccessObject the periodic transaction interactor
     */
    public UserLoginMediator(UserLoginInputBoundary loginInteractor,
                             UpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor,
                             UserAccountDataAccessInterface periodicTransactionDataAccessObject) {
        this.loginInteractor = loginInteractor;
        this.sharedLoginInteractor = null;
        this.updatePeriodicAtLoginInteractor = updatePeriodicAtLoginInteractor;
        this.periodicTransactionDataAccessObject = periodicTransactionDataAccessObject;
    }

    public UserLoginMediator(SharedAccountLoginInputBoundary interactor,
                             UpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor,
                             UserAccountDataAccessInterface periodicTransactionDataAccessObject) {
        this.loginInteractor = null;
        this.sharedLoginInteractor = interactor;
        this.updatePeriodicAtLoginInteractor = updatePeriodicAtLoginInteractor;
        this.periodicTransactionDataAccessObject = periodicTransactionDataAccessObject;
    }


    public void execute(UserLoginInputData userLoginInputData) {
        this.loginInteractor.execute(userLoginInputData);
    }
    public void execute(SharedAccountLoginInputData inputData) {
        this.sharedLoginInteractor.execute(inputData);
    }

    public void notifyLoginResult(boolean success, UpdatePeriodicAtLoginInputData updatePeriodicAtLoginInputData) {
        if (success) {
            updatePeriodicAtLoginInteractor.execute(updatePeriodicAtLoginInputData);
        }
    }
}

