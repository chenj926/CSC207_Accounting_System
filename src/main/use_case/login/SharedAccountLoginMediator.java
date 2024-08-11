package use_case.login;

import data_access.account.SharedAccountDataAccessInterface;
import use_case.update_periodic_at_login.SharedAccountUpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.SharedAccountUpdatePeriodicAtLoginInputData;

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

    SharedAccountLoginMediator(SharedAccountLoginInputBoundary loginInputData,
                             SharedAccountUpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor,
                             SharedAccountDataAccessInterface accountDataAccessObject){
        super(loginInputData, updatePeriodicAtLoginInteractor, accountDataAccessObject);
    }

}