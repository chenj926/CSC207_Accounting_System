package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewManagerModel;
import use_case.transaction.one_time.UserAccountOneTimeTransactionOutputBoundary;
import use_case.transaction.one_time.UserAccountOneTimeTransactionOutputData;

/**
 * The UserAccountOneTimeTransactionPresenter class implements the UserAccountOneTimeTransactionOutputBoundary interface.
 * It handles the presentation logic for one-time transactions, updating the view model and managing view transitions.
 *
 * @author Xile
 * @author Eric
 */
public class UserAccountOneTimeTransactionPresenter implements UserAccountOneTimeTransactionOutputBoundary {
    private final OneTimeTransactionViewModel viewModel;
    private final ViewManagerModel viewManager;
//    private ViewManagerModel viewManager;

    /**
     * Constructs a UserAccountOneTimeTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel   the view model to update the one-time transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public UserAccountOneTimeTransactionPresenter(OneTimeTransactionViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
//        this.transactionViewModel = transactionViewModel;
    }

    /**
     * Prepares the success view with the given one-time transaction output data.
     * Updates the transaction state and changes the view to the transaction view.
     *
     * @param data the one-time transaction output data containing transaction details and success status
     */
    @Override
    public void prepareSuccessView(UserAccountOneTimeTransactionOutputData data) {
        // update the current transaction sate
        OneTimeTransactionState oneTimeState = viewModel.getState();

        // set info
//        oneTimeState.setNewBalance(data.getNewBalance());
        oneTimeState.setId(data.getId());
        oneTimeState.setTransactionDate(data.getTransactionDate().toString());
        oneTimeState.setTransactionDescription(data.getTransactionDescription());
        oneTimeState.setTransactionCategory(data.getTransactionCategory());
//        oneTimeState.setUseCaseFailed(data.isUseCaseFailed());
        this.viewModel.setState(oneTimeState);
        oneTimeState.setSuccessMessage("One time transaction recorded successfully!");
        viewModel.firePropertyChanged();
        viewManager.setActiveViewName(viewModel.getViewName());

        // go back to home page 2
        viewManager.changeView("Homepage Two");
    }


    /**
     * Prepares the fail view with the given error message.
     * Updates the transaction state with the error message and clears the success message.
     *
     * @param error the error message to be presented in case of a failed one-time transaction attempt
     */
    @Override
    public void prepareFailView(String error) {
        OneTimeTransactionState oneTimeState = viewModel.getState();
        oneTimeState.setErrorMessage(error);
        oneTimeState.setSuccessMessage(null); // Clear success message on failure
        viewModel.firePropertyChanged();
    }
}









