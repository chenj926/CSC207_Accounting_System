package use_case.login;

import data_access.account.UserAccountDataAccessInterface;
import use_case.update_periodic_at_login.UserAccountUpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.UserAccountUpdatePeriodicAtLoginInputData;

/**
 * The UserAccountLoginMediator class mediates the interaction between the login process and periodic transaction updates.
 * It orchestrates actions post-login, like updating periodic transactions.
 *
 * @author Jessica
 */
public class UserAccountLoginMediator extends LoginMediator<
        UserAccountLoginInputBoundary,
        UserAccountUpdatePeriodicAtLoginInputBoundary,
        UserAccountDataAccessInterface,
        UserAccountLoginInputData,
        UserAccountUpdatePeriodicAtLoginInputData> {

    UserAccountLoginMediator(UserAccountLoginInputBoundary loginInputData,
                             UserAccountUpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor,
                             UserAccountDataAccessInterface accountDataAccessObject){
        super(loginInputData, updatePeriodicAtLoginInteractor, accountDataAccessObject);
    }

}

