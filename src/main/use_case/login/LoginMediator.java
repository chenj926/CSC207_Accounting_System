package use_case.login;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import use_case.transaction.periodic.PeriodicTransactionInteractor;
import use_case.update_periodic_at_login.UpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.UpdatePeriodicAtLoginInputData;

import java.time.LocalDate;

/**
 * The LoginMediator class mediates the interaction between the login process and periodic transaction updates.
 * It orchestrates actions post-login, like updating periodic transactions.
 *
 * @author Jessica
 */
public class LoginMediator {
    private final LoginInputBoundary loginInteractor;
    private final UpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor;
    private final UserAccountDataAccessInterface periodicTransactionDataAccessObject;

    /**
     * Constructs a LoginMediator object with the specified interactors.
     *
     * @param loginInteractor the login interactor
     * @param periodicTransactionDataAccessObject the periodic transaction interactor
     */
    public LoginMediator(LoginInputBoundary loginInteractor,
                         UpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor,
                         UserAccountDataAccessInterface periodicTransactionDataAccessObject) {
        this.loginInteractor = loginInteractor;
        this.updatePeriodicAtLoginInteractor = updatePeriodicAtLoginInteractor;
        this.periodicTransactionDataAccessObject = periodicTransactionDataAccessObject;
    }


    public void execute(LoginInputData loginInputData) {
        loginInteractor.execute(loginInputData);
    }

    public void notifyLoginResult(boolean success, UpdatePeriodicAtLoginInputData updatePeriodicAtLoginInputData) {
        if (success) {

            updatePeriodicAtLoginInteractor.execute(updatePeriodicAtLoginInputData);
        }
    }
}

