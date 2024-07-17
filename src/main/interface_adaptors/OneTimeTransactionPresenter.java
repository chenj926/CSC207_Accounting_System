package interface_adaptors;

import use_case.OneTimeTransactionOutputBoundary;
import use_case.OneTimeTransactionOutputData;

public class OneTimeTransactionPresenter implements OneTimeTransactionOutputBoundary {
    private final OneTimeTransactionViewModel viewModel;

    public OneTimeTransactionPresenter(OneTimeTransactionViewModel viewModel) {
        this.viewModel = viewModel;
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
}









