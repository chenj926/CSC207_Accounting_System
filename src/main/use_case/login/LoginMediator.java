package use_case.login;

import data_access.account.UserAccountDataAccessInterface;
import use_case.update_periodic_at_login.UserAccountUpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.UserAccountUpdatePeriodicAtLoginInputData;

/**
 * The LoginMediator class mediates the interaction between the login process and periodic transaction updates.
 * It orchestrates actions post-login, like updating periodic transactions.
 *
 * @author Jessica
 */
public class LoginMediator {
    private final UserAccountLoginInputBoundary loginInteractor;
    private final SharedAccountLoginInputBoundary sharedLoginInteractor;
    private final UserAccountUpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor;
    private final UserAccountDataAccessInterface periodicTransactionDataAccessObject;

    /**
     * Constructs a LoginMediator object with the specified interactors.
     *
     * @param loginInteractor the login interactor
     * @param periodicTransactionDataAccessObject the periodic transaction interactor
     */
    public LoginMediator(UserAccountLoginInputBoundary loginInteractor,
                         UserAccountUpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor,
                         UserAccountDataAccessInterface periodicTransactionDataAccessObject) {
        this.loginInteractor = loginInteractor;
        this.sharedLoginInteractor = null;
        this.updatePeriodicAtLoginInteractor = updatePeriodicAtLoginInteractor;
        this.periodicTransactionDataAccessObject = periodicTransactionDataAccessObject;
    }

    public LoginMediator(SharedAccountLoginInputBoundary interactor,
                         UserAccountUpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor,
                         UserAccountDataAccessInterface periodicTransactionDataAccessObject) {
        this.loginInteractor = null;
        this.sharedLoginInteractor = interactor;
        this.updatePeriodicAtLoginInteractor = updatePeriodicAtLoginInteractor;
        this.periodicTransactionDataAccessObject = periodicTransactionDataAccessObject;
    }


    public void execute(UserAccountLoginInputData userAccountLoginInputData) {
        this.loginInteractor.execute(userAccountLoginInputData);
    }
    public void execute(SharedAccountLoginInputData sharedAccountLoginInputData) {
        this.sharedLoginInteractor.execute(sharedAccountLoginInputData);
    }

    public void notifyLoginResult(boolean success, UserAccountUpdatePeriodicAtLoginInputData userAccountUpdatePeriodicAtLoginInputData) {
        if (success) {
            updatePeriodicAtLoginInteractor.execute(userAccountUpdatePeriodicAtLoginInputData);
        }
    }
}

