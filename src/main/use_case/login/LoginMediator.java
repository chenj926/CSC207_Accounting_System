package use_case.login;

import data_access.account.AccountDataAccessInterface;
import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputData;

/**
 * The LoginMediator class mediates the interaction between the login process and periodic transaction updates.
 * It orchestrates actions post-login, like updating periodic transactions.
 *
 * @author Jessica
 */
public class LoginMediator<LInputBoundary extends LoginInputBoundary,
        UPLInputBoundary extends AccountUpdatePeriodicAtLoginInputBoundary,
        DataAccessInterface extends AccountDataAccessInterface,
        LInputData extends LoginInputData,
        UPLInputData extends AccountUpdatePeriodicAtLoginInputData>{

    protected final LInputBoundary loginInteractor;
    protected final UPLInputBoundary updatePeriodicAtLoginInteractor;
    protected final DataAccessInterface periodicTransactionDataAccessObject;

    /**
     * Constructs a LoginMediator object with the specified interactors.
     *
     * @param loginInteractor the login interactor
     * @param periodicTransactionDataAccessObject the periodic transaction interactor
     */
    public LoginMediator(LInputBoundary loginInteractor,
                                    UPLInputBoundary updatePeriodicAtLoginInteractor,
                                    DataAccessInterface periodicTransactionDataAccessObject) {
        this.loginInteractor = loginInteractor;
        this.updatePeriodicAtLoginInteractor = updatePeriodicAtLoginInteractor;
        this.periodicTransactionDataAccessObject = periodicTransactionDataAccessObject;
    }


    public void execute(LInputData loginInputData) {
        this.loginInteractor.execute(loginInputData);
    }


    public void notifyLoginResult(boolean success, UPLInputData updatePeriodicAtLoginInputData) {
        if (success) {
            updatePeriodicAtLoginInteractor.execute(updatePeriodicAtLoginInputData);
        }
    }
}
