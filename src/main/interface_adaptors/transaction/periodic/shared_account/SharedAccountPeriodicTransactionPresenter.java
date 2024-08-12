package interface_adaptors.transaction.periodic.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionPresenter;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputBoundary;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;

/**
 * The SharedAccountPeriodicTransactionPresenter class implements the UserAccountPeriodicTransactionOutputBoundary interface.
 * It handles the presentation logic for shared account periodic transactions, updating the view model and managing view transitions.
 *
 */
public class SharedAccountPeriodicTransactionPresenter
        extends PeriodicTransactionPresenter<SharedAccountPeriodicTransactionViewModel, SharedAccountPeriodicTransactionState>
        implements SharedAccountPeriodicTransactionOutputBoundary {

    /**
     * Constructs a SharedAccountPeriodicTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel   the view model to update the shared account periodic transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public SharedAccountPeriodicTransactionPresenter(SharedAccountPeriodicTransactionViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the success view with the given shared account periodic transaction output data.
     * Updates the transaction state and changes the view to the transaction view.
     *
     * @param data the shared account periodic transaction output data containing transaction details and success status
     */
    @Override
    public void prepareSuccessView(SharedAccountPeriodicTransactionOutputData data) {
        // Update the current transaction state
        SharedAccountPeriodicTransactionState sharedState = viewModel.getState();

        // Set transaction details
        sharedState.setTransactionAmount(String.valueOf(data.getTransactionAmount()));
        sharedState.setTransactionStartDate(data.getTransactionStartDate().toString());
        sharedState.setTransactionDescription(data.getTransactionDescription());
        sharedState.setTransactionPeriod(String.valueOf(data.getTransactionPeriod()));
        sharedState.setTransactionEndDate(data.getTransactionEndDate().toString());
        sharedState.setTransactionCategory(data.getTransactionCategory());
        sharedState.setId(data.getId());
        sharedState.setSuccessMessage("Shared Account Periodic Transaction Recorded successfully!");
        sharedState.setErrorMessage(null); // Clear error message on success

        // Fire property change
        viewModel.setState(sharedState);
        viewModel.firePropertyChanged();

        // Set the active view and navigate back to the transaction page
        viewManager.setActiveViewName(viewModel.getViewName());
        viewManager.changeView("Shared Account Homepage Two");
    }
}

