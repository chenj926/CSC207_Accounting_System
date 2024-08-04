package use_case.login;

import use_case.transaction.periodic.PeriodicTransactionInputBoundary;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * The LoginMediator class mediates the interaction between the login process and periodic transaction updates.
 *
 * @author Jessica
 */
public class LoginMediator {
    private final LoginInputBoundary loginInteractor;
    private final PeriodicTransactionInputBoundary periodicTransactionInteractor;
    private final DateTimeFormatter formatter;

    /**
     * Constructs a LoginMediator object with the specified interactors.
     *
     * @param loginInteractor the login interactor
     * @param periodicTransactionInteractor the periodic transaction interactor
     */
    public LoginMediator(LoginInputBoundary loginInteractor, PeriodicTransactionInputBoundary periodicTransactionInteractor) {
        this.loginInteractor = loginInteractor;
        this.periodicTransactionInteractor = periodicTransactionInteractor;
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
    }

    public void execute(LoginInputData loginInputData) {
        loginInteractor.execute(loginInputData);

        LocalDate currentDate = LocalDate.now();
        String currentDateStr = currentDate.format(formatter);

        // periodicTransactionInteractor.updateOnLogin(currentDateStr);
    }
}

