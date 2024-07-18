package interface_adaptors;

import use_case.OneTimeTransactionOutputBoundary;
import use_case.OneTimeTransactionOutputData;

public class OneTimeTransactionPresenter implements OneTimeTransactionOutputBoundary {
    private final OneTimeTransactionViewModel viewModel;
    private final TransactionViewModel transactionViewModel;

    public OneTimeTransactionPresenter(OneTimeTransactionViewModel viewModel, TransactionViewModel transactionViewModel) {
        this.viewModel = viewModel;
        this.transactionViewModel = transactionViewModel;
    }

    @Override
    public void prepareSuccessView(OneTimeTransactionOutputData data) {
        OneTimeTransactionState state = viewModel.getState();
        state.setNewBalance(data.getNewBalance());
        state.setTransactionDate(data.getTransactionDate().toString());
        state.setTransactionDescription(data.getTransactionDescription());
        state.setTransactionCategory(data.getTransactionCategory());
        state.setUseCaseFailed(data.isUseCaseFailed());
        state.setSuccessMessage("One-time transaction recorded successfully!");
        viewModel.notifyPropertyChange();
    }


    @Override
    public void prepareFailView(String error) {
        OneTimeTransactionState state = viewModel.getState();
        state.setError(error);
        state.setSuccessMessage(null); // Clear success message on failure
        viewModel.notifyPropertyChange();
    }


    public void handleCancel() {
        transactionViewModel.selectOneTimeTransaction();
    }
}









