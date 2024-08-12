package interface_adaptors.transaction.one_time.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.OneTimeTransactionPresenter;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputBoundary;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;

/**
 * The UserOneTimeTransactionPresenter class implements the UserAccountOneTimeTransactionOutputBoundary interface.
 * It handles the presentation logic for one-time transactions, updating the view model and managing view transitions.
 *
 * @author Xile
 * @author Eric
 */
public class UserOneTimeTransactionPresenter extends OneTimeTransactionPresenter<
        UserOneTimeTransactionViewModel,
        UserOneTimeTransactionState>
        implements UserAccountOneTimeTransactionOutputBoundary {


    /**
     * Constructs a UserOneTimeTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel   the view model to update the one-time transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public UserOneTimeTransactionPresenter(UserOneTimeTransactionViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
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
        UserOneTimeTransactionState oneTimeState = (UserOneTimeTransactionState) viewModel.getState();

        // set info
        oneTimeState.setId(data.getId());
        oneTimeState.setTransactionDate(data.getTransactionDate().toString());
        oneTimeState.setTransactionDescription(data.getTransactionDescription());
        oneTimeState.setTransactionCategory(data.getTransactionCategory());

        this.viewModel.setState(oneTimeState);
        oneTimeState.setSuccessMessage("One time transaction recorded successfully!");
        viewModel.firePropertyChanged();
        viewManager.setActiveViewName(viewModel.getViewName());

        // go back to home page 2
        viewManager.changeView("Homepage Two");
    }

}









