package interface_adaptors.transaction;

import use_case.transaction.TransactionInputBoundary;

public abstract class TransactionController<
        I extends TransactionInputBoundary,
        V extends TransactionViewModel> {
    protected final I transactionInputBoundary;
    protected final V viewModel;

    public TransactionController(I transactionInputBoundary, V viewModel) {
        this.transactionInputBoundary = transactionInputBoundary;
        this.viewModel = viewModel;
    }

}
