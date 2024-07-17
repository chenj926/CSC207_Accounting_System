package interface_adaptors;

import use_case.PeriodicTransactionOutputBoundary;
import use_case.PeriodicTransactionOutputData;

public class PeriodicTransactionPresenter implements PeriodicTransactionOutputBoundary {
    private final PeriodicTransactionViewModel viewModel;
    private final TransactionViewModel transactionViewModel;

    public PeriodicTransactionPresenter(PeriodicTransactionViewModel viewModel, TransactionViewModel transactionViewModel) {
        this.viewModel = viewModel;
        this.transactionViewModel = transactionViewModel;
    }

    @Override
    public void prepareSuccessView(PeriodicTransactionOutputData data) {
        PeriodicTransactionState state = viewModel.getState();
        state.setTransactionAmount(data.getTransactionAmount());
        state.setTransactionStartDate(data.getTransactionStartDate().toString());
        state.setTransactionDescription(data.getTransactionDescription());
        state.setTransactionPeriod(data.getTransactionPeriod());
        state.setTransactionEndDate(data.getTransactionEndDate().toString());
        state.setSuccessMessage("Periodic transaction recorded successfully!");
        viewModel.notifyPropertyChange();
    }

    @Override
    public void prepareFailView(String error) {
        PeriodicTransactionState state = viewModel.getState();
        state.setError(error);
        state.setSuccessMessage(null); // Clear success message on failure
        viewModel.notifyPropertyChange();
    }


    public void handleCancel() {
        transactionViewModel.selectPeriodicTransaction();
    }
}
