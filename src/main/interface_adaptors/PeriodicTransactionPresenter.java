package interface_adaptors;

import use_case.PeriodicTransactionOutputBoundary;
import use_case.PeriodicTransactionOutputData;

public class PeriodicTransactionPresenter implements PeriodicTransactionOutputBoundary {
    private final PeriodicTransactionViewModel viewModel;

    public PeriodicTransactionPresenter(PeriodicTransactionViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(PeriodicTransactionOutputData data) {
        viewModel.setTransactionAmount(data.getTransactionAmount());
        viewModel.setTransactionStartDate(data.getTransactionStartDate().toString());
        viewModel.setTransactionEndDate(data.getTransactionEndDate().toString());
        viewModel.setTransactionPeriod(data.getTransactionPeriod());
        viewModel.setTransactionDescription(data.getTransactionDescription());
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        viewModel.setError(error);
        viewModel.firePropertyChanged();
    }
}

