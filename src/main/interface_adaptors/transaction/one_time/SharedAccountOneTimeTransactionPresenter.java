package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewManagerModel;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionOutputBoundary;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionOutputData;

/**
 * The SharedAccountOneTimeTransactionPresenter class implements the SharedAccountOneTimeTransactionOutputBoundary interface.
 * It handles the presentation logic for shared account one-time transactions, updating the view model and managing view transitions.
 */
public class SharedAccountOneTimeTransactionPresenter implements SharedAccountOneTimeTransactionOutputBoundary {
    private final SharedAccountOneTimeTransactionViewModel viewModel;
    private final ViewManagerModel viewManager;

    /**
     * Constructs a SharedAccountOneTimeTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel   the view model to update the shared account one-time transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public SharedAccountOneTimeTransactionPresenter(SharedAccountOneTimeTransactionViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    /**
     * Prepares the success view with the given shared account one-time transaction output data.
     * Updates the transaction state and changes the view to the transaction view.
     *
     * @param data the shared account one-time transaction output data containing transaction details and success status
     */
    @Override
    public void prepareSuccessView(SharedAccountOneTimeTransactionOutputData data) {
        // Update the current transaction state
        SharedAccountOneTimeTransactionState transactionState = viewModel.getState();

        // Set transaction information
        transactionState.setTransactionDate(data.getTransactionDate().toString());
        transactionState.setTransactionDescription(data.getTransactionDescription());
        transactionState.setTransactionCategory(data.getTransactionCategory());
//        transactionState.setNewSharedAccountBalance(data.getNewSharedAccountBalance());
        String[] ids = data.getId().split(";");
        transactionState.setResponsibleUserIds(ids[1]);
        transactionState.setShareId(ids[0]);
        transactionState.setSuccessMessage("Shared account one-time transaction recorded successfully!");

        // Update the view model state
        viewModel.setState(transactionState);
        viewModel.firePropertyChanged();

        // Change the view to the transaction view
        viewManager.setActiveViewName(viewModel.getViewName());
        viewManager.changeView("Shared Account Transaction");
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the transaction state with the error message and clears the success message.
     *
     * @param error the error message to be presented in case of a failed shared account one-time transaction attempt
     */
    @Override
    public void prepareFailView(String error) {
        SharedAccountOneTimeTransactionState transactionState = viewModel.getState();
        transactionState.setErrorMessage(error);
        transactionState.setSuccessMessage(null); // Clear success message on failure
        viewModel.firePropertyChanged();
    }
}

