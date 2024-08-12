package interface_adaptors.transaction.periodic;

import use_case.transaction.periodic.PeriodicTransactionInputBoundary;

public abstract class PeriodicTransactionController<
        I extends PeriodicTransactionInputBoundary,
        V extends PeriodicTransactionViewModel> {

    protected final I periodicTransactionInputBoundary;
    protected final V viewModel;

    /**
     * Constructs a UserAccountPeriodicTransactionController object with the specified use case interactor and view model.
     *
     * @param periodicTransactionInputBoundary the use case interactor for periodic transactions
     * @param viewModel                        the view model to update the transaction state
     */
    public PeriodicTransactionController(I periodicTransactionInputBoundary, V viewModel) {
        this.periodicTransactionInputBoundary = periodicTransactionInputBoundary;
        this.viewModel = viewModel;
    }

}
