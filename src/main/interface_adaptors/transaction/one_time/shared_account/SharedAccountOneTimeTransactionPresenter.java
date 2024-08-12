package interface_adaptors.transaction.one_time.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.AccountOneTimeTransactionPresenter;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputBoundary;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;

/**
 * The SharedAccountOneTimeTransactionPresenter class implements the SharedAccountOneTimeTransactionOutputBoundary interface.
 * It handles the presentation logic for shared account one-time transactions, updating the view model and managing view transitions.
 */
public class SharedAccountOneTimeTransactionPresenter extends AccountOneTimeTransactionPresenter<
        SharedAccountOneTimeTransactionViewModel,
        SharedAccountOneTimeTransactionState>
        implements SharedAccountOneTimeTransactionOutputBoundary {


    /**
     * Constructs a SharedAccountOneTimeTransactionPresenter object with the specified view model and view manager model.
     *
     * @param viewModel   the view model to update the shared account one-time transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public SharedAccountOneTimeTransactionPresenter(SharedAccountOneTimeTransactionViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
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
        transactionState.setId(data.getId());

        this.viewModel.setState(transactionState);
        transactionState.setSuccessMessage("Shared account one-time transaction recorded successfully!");
        viewModel.firePropertyChanged();

        // Change the view to the transaction view
        viewManager.setActiveViewName(viewModel.getViewName());
        viewManager.changeView("Shared Account Homepage Two");
    }
}

