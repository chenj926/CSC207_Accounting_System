package use_case.login.shared_account;

import data_access.account.shared_account.SharedAccountDataAccessInterface;
import use_case.login.LoginMediator;
import use_case.update_periodic_at_login.shared_account.SharedAccountUpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.shared_account.SharedAccountUpdatePeriodicAtLoginInputData;

/**
 * The SharedAccountLoginMediator class mediates the interaction between the login process and periodic transaction updates.
 * It orchestrates actions post-login, like updating periodic transactions.
 *
 * @author Jessica
 */
public class SharedAccountLoginMediator extends LoginMediator<
        SharedAccountLoginInputBoundary,
        SharedAccountUpdatePeriodicAtLoginInputBoundary,
        SharedAccountDataAccessInterface,
        SharedAccountLoginInputData,
        SharedAccountUpdatePeriodicAtLoginInputData> {

    public SharedAccountLoginMediator(SharedAccountLoginInputBoundary loginInputData,
                                      SharedAccountUpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor,
                                      SharedAccountDataAccessInterface accountDataAccessObject){
        super(loginInputData, updatePeriodicAtLoginInteractor, accountDataAccessObject);
    }

}