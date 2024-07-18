package use_case;

import data_access.UserAccountDataAccessInterface;

public class TransactionInteractor implements OneTimeTransactionInputBoundary, PeriodicTransactionInputBoundary {
    private final UserAccountDataAccessInterface userDataAccessObject;

    private final OneTimeTransactionOutputBoundary oneTimePresenter;
    private final PeriodicTransactionOutputBoundary periodicPresenter;

    private final OneTimeTransactionInputBoundary oneTimeTransactionInteractor;
    private final PeriodicTransactionInputBoundary periodicTransactionInteractor;

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

    @Override
    public void execute(OneTimeTransactionInputData oneTimeTransactionInputData) {
        oneTimeTransactionInteractor.execute(oneTimeTransactionInputData);
    }

    @Override
    public void execute(PeriodicTransactionInputData periodicTransactionInputData) {
        periodicTransactionInteractor.execute(periodicTransactionInputData);
    }
}

