package interface_adaptors.transaction;

import interface_adaptors.ViewManagerModel;

public abstract class TransactionPresenter<
        V extends TransactionViewModel,
        S extends TransactionState> {

    protected final V viewModel;
    protected final ViewManagerModel viewManager;

    public TransactionPresenter(V viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    public abstract void prepareFailView(String error);
}
