package use_case.transaction;

import data_access.UserAccountDataAccessInterface;


/**
 * The TransactionInteractor class implements both OneTimeTransactionInputBoundary and PeriodicTransactionInputBoundary interfaces.
 * It acts as a mediator for handling both one-time and periodic transaction operations by delegating them to the respective interactors.
 *
 * @author Jessica
 */
public class TransactionInteractor implements OneTimeTransactionInputBoundary, PeriodicTransactionInputBoundary {
    private final UserAccountDataAccessInterface userDataAccessObject;

    private final OneTimeTransactionOutputBoundary oneTimePresenter;
    private final PeriodicTransactionOutputBoundary periodicPresenter;

    private final OneTimeTransactionInputBoundary oneTimeTransactionInteractor;
    private final PeriodicTransactionInputBoundary periodicTransactionInteractor;

    /**
     * Constructs a TransactionInteractor object with the specified data access interface, output boundaries, and transaction interactors.
     *
     * @param userAccountDataAccessInterface the data access interface for user data
     * @param oneTimeTransactionOutputBoundary the output boundary for presenting one-time transaction results
     * @param periodicTransactionOutputBoundary the output boundary for presenting periodic transaction results
     * @param oneTimeTransactionInteractor the interactor for handling one-time transactions
     * @param periodicTransactionInteractor the interactor for handling periodic transactions
     */
    public TransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                 OneTimeTransactionOutputBoundary oneTimeTransactionOutputBoundary,
                                 PeriodicTransactionOutputBoundary periodicTransactionOutputBoundary,
                                 OneTimeTransactionInputBoundary oneTimeTransactionInteractor,
                                 PeriodicTransactionInputBoundary periodicTransactionInteractor) {

        this.userDataAccessObject = userAccountDataAccessInterface;

        this.oneTimePresenter = oneTimeTransactionOutputBoundary;
        this.periodicPresenter = periodicTransactionOutputBoundary;

        this.oneTimeTransactionInteractor = oneTimeTransactionInteractor;
        this.periodicTransactionInteractor = periodicTransactionInteractor;
    }

    /**
     * Executes the one-time transaction process with the given input data by delegating to the respective interactor.
     *
     * @param oneTimeTransactionInputData the input data required for the one-time transaction process
     */
    @Override
    public void execute(OneTimeTransactionInputData oneTimeTransactionInputData) {
        oneTimeTransactionInteractor.execute(oneTimeTransactionInputData);
    }

    /**
     * Executes the periodic transaction process with the given input data by delegating to the respective interactor.
     *
     * @param periodicTransactionInputData the input data required for the periodic transaction process
     */
    @Override
    public void execute(PeriodicTransactionInputData periodicTransactionInputData) {
        periodicTransactionInteractor.execute(periodicTransactionInputData);
    }
}

