package interface_adaptors.transaction.periodic.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionPresenter;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputBoundary;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

/**
 * The UserAccountPeriodicTransactionPresenter class implements the UserAccountPeriodicTransactionOutputBoundary interface.
 * It handles the presentation logic for periodic transactions, updating the view model and managing view transitions.
 *
 * @author Eric
 * @author Xile
 */
public class UserAccountPeriodicTransactionPresenter
        extends PeriodicTransactionPresenter<UserAccountPeriodicTransactionViewModel, UserAccountPeriodicTransactionState>
        implements UserAccountPeriodicTransactionOutputBoundary {

    /**
     * Constructs a UserAccountPeriodicTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel  the view model to update the periodic transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public UserAccountPeriodicTransactionPresenter(UserAccountPeriodicTransactionViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the success view with the given periodic transaction output data.
     * Updates the transaction state and changes the view to the transaction view.
     *
     * @param data the periodic transaction output data containing transaction details and success status
     */
    @Override
    public void prepareSuccessView(UserAccountPeriodicTransactionOutputData data) {
        // update the current transaction sate
        UserAccountPeriodicTransactionState periodicState = this.viewModel.getState();

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
        this.viewManager.changeView("Homepage Two");
    }
}
