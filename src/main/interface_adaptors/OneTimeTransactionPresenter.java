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
        viewModel.setNewBalance(data.getNewBalance());
        viewModel.setTransactionDate(data.getTransactionDate().toString());
        viewModel.setTransactionDescription(data.getTransactionDescription());
        viewModel.setTransactionCategory(data.getTransactionCategory());
        viewModel.setUseCaseFailed(data.isUseCaseFailed());
        viewModel.setSuccessMessage("One-time transaction recorded successfully!");
        viewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String error) {
        viewModel.setError(error);
        viewModel.setSuccessMessage(null); // Clear success message on failure
        viewModel.firePropertyChange();
    }
}







