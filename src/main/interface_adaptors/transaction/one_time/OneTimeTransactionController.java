package interface_adaptors.transaction.one_time;

import use_case.transaction.one_time.OneTimeTransactionInputBoundary;

public abstract class OneTimeTransactionController<
        I extends OneTimeTransactionInputBoundary,
        V extends OneTimeTransactionViewModel> {

    protected final I oneTimeTransactionInteractor;
    protected final V viewModel;

    /**
     * Constructs a OneTimeTransactionController object with the specified use case interactor and view model.
     *
     * @param oneTimeTransactionInteractor the use case interactor for one-time transactions
     * @param viewModel                               the view model to update the transaction state
     */
    public OneTimeTransactionController(I oneTimeTransactionInteractor,
                                        V viewModel) {
        this.oneTimeTransactionInteractor = oneTimeTransactionInteractor;
        this.viewModel = viewModel;
    }

}
