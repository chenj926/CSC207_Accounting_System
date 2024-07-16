package interface_adaptors;

import use_case.OneTimeTransactionOutputBoundary;
import use_case.OneTimeTransactionOutputData;

public class OneTimeTransactionPresenter implements OneTimeTransactionOutputBoundary {
    private final OneTimeTransactionViewModel viewModel;
    private ViewManagerModel viewManagerModel;

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
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        viewModel.setError(message);
        viewModel.firePropertyChanged();
    }
}






