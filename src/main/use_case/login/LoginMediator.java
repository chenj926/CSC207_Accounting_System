package use_case.login;

import use_case.transaction.periodic.PeriodicTransactionInputBoundary;
import java.time.LocalDate;

/**
 * The LoginMediator class mediates the interaction between the login process and periodic transaction updates.
 * It orchestrates actions post-login, like updating periodic transactions.
 *
 * @author Jessica
 */
public class LoginMediator {
    private final LoginInputBoundary loginInteractor;
    private final PeriodicTransactionInputBoundary periodicTransactionInteractor;

    /**
     * Constructs a LoginMediator object with the specified interactors.
     *
     * @param loginInteractor the login interactor
     * @param periodicTransactionInteractor the periodic transaction interactor
     */
    public LoginMediator(LoginInputBoundary loginInteractor, PeriodicTransactionInputBoundary periodicTransactionInteractor, LoginOutputBoundary loginOutput) {
        this.loginInteractor = loginInteractor;
        this.periodicTransactionInteractor = periodicTransactionInteractor;
    }

    public void execute(LoginInputData loginInputData) {
        loginInteractor.execute(loginInputData);
    }

    public void notifyLoginResult(boolean success, String userId) {
        if (success) {
            LocalDate currentDate = LocalDate.now();
            // periodicTransactionInteractor.updateTransactionsBasedOnDate(userId, currentDate);
        }
    }
}

