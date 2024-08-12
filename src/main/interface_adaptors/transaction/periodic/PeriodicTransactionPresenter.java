package interface_adaptors.transaction.periodic;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.TransactionPresenter;

public abstract class PeriodicTransactionPresenter<
        V extends PeriodicTransactionViewModel<S>,
        S extends PeriodicTransactionState> extends TransactionPresenter<V, S> {

    /**
     * Constructs a UserAccountPeriodicTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel  the view model to update the periodic transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public PeriodicTransactionPresenter(V viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the transaction state with the error message and clears the success message.
     *
     * @param error the error message to be presented in case of a failed periodic transaction attempt
     */
    @Override
    public void prepareFailView(String error) {
        S periodicState = this.viewModel.getState();
        periodicState.setErrorMessage(error);
        periodicState.setSuccessMessage(null); // Clear success message on failure
        this.viewModel.setState(periodicState);
        this.viewModel.firePropertyChanged();
    }
}
