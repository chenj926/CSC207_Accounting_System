package interface_adaptors.transaction.periodic;

import interface_adaptors.ViewManagerModel;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionOutputBoundary;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionOutputData;

/**
 * The SharedAccountPeriodicTransactionPresenter class implements the UserAccountPeriodicTransactionOutputBoundary interface.
 * It handles the presentation logic for shared account periodic transactions, updating the view model and managing view transitions.
 *
 */
public class SharedAccountPeriodicTransactionPresenter implements SharedAccountPeriodicTransactionOutputBoundary {
    private final SharedAccountPeriodicTransactionViewModel viewModel;
    private final ViewManagerModel viewManager;

    /**
     * Constructs a SharedAccountPeriodicTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel   the view model to update the shared account periodic transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public SharedAccountPeriodicTransactionPresenter(SharedAccountPeriodicTransactionViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
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
        sharedState.setResponsibleUserIds(data.getResponsibleUserIds());
        sharedState.setSuccessMessage("Shared Account Periodic Transaction Recorded successfully!");
        sharedState.setError(null); // Clear error message on success

        // Fire property change
        viewModel.setState(sharedState);
        viewModel.firePropertyChanged();

        // Set the active view and navigate back to the transaction page
        viewManager.setActiveViewName(viewModel.getViewName());
        viewManager.changeView("Shared Account Transaction");
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the transaction state with the error message and clears the success message.
     *
     * @param error the error message to be presented in case of a failed shared account periodic transaction attempt
     */
    @Override
    public void prepareFailView(String error) {
        SharedAccountPeriodicTransactionState sharedState = viewModel.getState();
        sharedState.setError(error);
        sharedState.setSuccessMessage(null); // Clear success message on failure
        viewModel.setState(sharedState);
        viewModel.firePropertyChanged();
    }
}

