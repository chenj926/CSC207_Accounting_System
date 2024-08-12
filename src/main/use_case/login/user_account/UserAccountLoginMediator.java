package use_case.login.user_account;

import data_access.account.user_account.UserAccountDataAccessInterface;
import use_case.login.LoginMediator;
import use_case.update_periodic_at_login.user_account.UserAccountUpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.user_account.UserAccountUpdatePeriodicAtLoginInputData;

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

    public UserAccountLoginMediator(UserAccountLoginInputBoundary loginInputData,
                             UserAccountUpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor,
                             UserAccountDataAccessInterface accountDataAccessObject){
        super(loginInputData, updatePeriodicAtLoginInteractor, accountDataAccessObject);
    }

}

