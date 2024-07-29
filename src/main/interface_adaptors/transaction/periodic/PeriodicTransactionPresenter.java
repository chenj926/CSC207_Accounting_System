package interface_adaptors.transaction.periodic;

import interface_adaptors.ViewManagerModel;
import use_case.transaction.periodic.PeriodicTransactionOutputBoundary;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

/**
 * The PeriodicTransactionPresenter class implements the PeriodicTransactionOutputBoundary interface.
 * It handles the presentation logic for periodic transactions, updating the view model and managing view transitions.
 *
 * @author Eric
 * @author Xile
 */
public class PeriodicTransactionPresenter implements PeriodicTransactionOutputBoundary {
    private final PeriodicTransactionViewModel viewModel;
    private final ViewManagerModel viewManager;

    /**
     * Constructs a PeriodicTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel  the view model to update the periodic transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public PeriodicTransactionPresenter(PeriodicTransactionViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    /**
     * Prepares the success view with the given periodic transaction output data.
     * Updates the transaction state and changes the view to the transaction view.
     *
     * @param data the periodic transaction output data containing transaction details and success status
     */
    @Override
    public void prepareSuccessView(PeriodicTransactionOutputData data) {
        // update the current transaction sate
        PeriodicTransactionState periodicState = this.viewModel.getState();

        // set info
        periodicState.setTransactionAmount(String.valueOf(data.getTransactionAmount()));
        periodicState.setTransactionStartDate(data.getTransactionStartDate().toString());
        periodicState.setTransactionDescription(data.getTransactionDescription());
        periodicState.setTransactionPeriod(String.valueOf(data.getTransactionPeriod()));
        periodicState.setTransactionEndDate(data.getTransactionEndDate().toString());
        periodicState.setSuccessMessage("Period Transaction Recorded successfully!");
        periodicState.setError(null); // Clear err message on failure

        // fire property change
        this.viewModel.setState(periodicState);
        this.viewModel.firePropertyChanged();

        this.viewManager.setActiveViewName(viewModel.getViewName());

        // go back to home page 2
        this.viewManager.changeView("Transaction");
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the transaction state with the error message and clears the success message.
     *
     * @param error the error message to be presented in case of a failed periodic transaction attempt
     */
    @Override
    public void prepareFailView(String error) {
        PeriodicTransactionState periodicState = this.viewModel.getState();
        periodicState.setError(error);
        periodicState.setSuccessMessage(null); // Clear success message on failure
        this.viewModel.setState(periodicState);
        this.viewModel.firePropertyChanged();
    }
}
