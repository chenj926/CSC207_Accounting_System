package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.TransactionPresenter;

public abstract class OneTimeTransactionPresenter<
        V extends OneTimeTransactionViewModel<S>,
        S extends OneTimeTransactionState> extends TransactionPresenter<V, S> {

    /**
     * Constructs a UserOneTimeTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel   the view model to update the one-time transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public OneTimeTransactionPresenter(V viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the transaction state with the error message and clears the success message.
     *
     * @param error the error message to be presented in case of a failed one-time transaction attempt
     */
    @Override
    public void prepareFailView (String error){
        S oneTimeState = this.viewModel.getState();
        oneTimeState.setErrorMessage(error);
        oneTimeState.setSuccessMessage(null); // Clear success message on failure
        viewModel.firePropertyChanged();
    }
}












