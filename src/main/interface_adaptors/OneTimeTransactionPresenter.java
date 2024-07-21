package interface_adaptors;

import use_case.OneTimeTransactionOutputBoundary;
import use_case.OneTimeTransactionOutputData;

public class OneTimeTransactionPresenter implements OneTimeTransactionOutputBoundary {
    private final OneTimeTransactionViewModel viewModel;
    private final ViewManagerModel viewManager;

    public OneTimeTransactionPresenter(OneTimeTransactionViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
//        this.transactionViewModel = transactionViewModel;
    }

    @Override
    public void prepareSuccessView(OneTimeTransactionOutputData data) {
        // update the current transaction sate
        OneTimeTransactionState oneTimeState = viewModel.getState();

        // set info
        oneTimeState.setNewBalance(data.getNewBalance());
        oneTimeState.setTransactionDate(data.getTransactionDate().toString());
        oneTimeState.setTransactionDescription(data.getTransactionDescription());
        oneTimeState.setTransactionCategory(data.getTransactionCategory());
        oneTimeState.setUseCaseFailed(data.isUseCaseFailed());
        this.viewModel.setState(oneTimeState);
        oneTimeState.setSuccessMessage("One time transaction recorded successfully!");
        viewModel.firePropertyChanged();
        viewManager.setActiveViewName(viewModel.getViewName());

        // go back to home page 2
        viewManager.changeView("Transaction");
    }


    @Override
    public void prepareFailView(String error) {
        OneTimeTransactionState oneTimeState = viewModel.getState();
        oneTimeState.setErrorMessage(error);
        oneTimeState.setSuccessMessage(null); // Clear success message on failure
        viewModel.firePropertyChanged();
    }
}









