package use_case.login;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import use_case.transaction.periodic.PeriodicTransactionInteractor;

import java.time.LocalDate;

/**
 * The LoginMediator class mediates the interaction between the login process and periodic transaction updates.
 * It orchestrates actions post-login, like updating periodic transactions.
 *
 * @author Jessica
 */
public class LoginMediator {
    private final LoginInputBoundary loginInteractor;
    private final UserAccountDataAccessInterface periodicTransactionDataAccessObject;

    /**
     * Constructs a LoginMediator object with the specified interactors.
     *
     * @param loginInteractor the login interactor
     * @param periodicTransactionDataAccessObject the periodic transaction interactor
     */
    public LoginMediator(LoginInputBoundary loginInteractor, UserAccountDataAccessInterface periodicTransactionDataAccessObject) {
        this.loginInteractor = loginInteractor;
        this.periodicTransactionDataAccessObject = periodicTransactionDataAccessObject;
    }


    public void execute(LoginInputData loginInputData) {
        loginInteractor.execute(loginInputData);
    }

    public void notifyLoginResult(boolean success, String userId) {
        if (success) {
            LocalDate currentDate = LocalDate.now();
            UserAccount userAccount = periodicTransactionDataAccessObject.getById(userId);
            PeriodicTransactionInteractor periodicTransactionInteractor = new PeriodicTransactionInteractor(periodicTransactionDataAccessObject, null, userAccount); // Assuming presenter is null for this context
            // periodicTransactionInteractor.updateTransactionsBasedOnDate(userId, currentDate);
        }
    }
}

