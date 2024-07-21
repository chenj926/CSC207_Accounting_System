package interface_adaptors;

import use_case.PeriodicTransactionOutputBoundary;
import use_case.PeriodicTransactionOutputData;

public class PeriodicTransactionPresenter implements PeriodicTransactionOutputBoundary {
    private final PeriodicTransactionViewModel viewModel;
    private final ViewManagerModel viewManager;

    public PeriodicTransactionPresenter(PeriodicTransactionViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void prepareSuccessView(PeriodicTransactionOutputData data) {
        // update the current transaction sate
        PeriodicTransactionState periodicState = this.viewModel.getState();

        // set info
        periodicState.setTransactionAmount(String.valueOf(data.getTransactionAmount()));
        periodicState.setTransactionStartDate(data.getTransactionStartDate().toString());
        periodicState.setTransactionDescription(data.getTransactionDescription());
        periodicState.setTransactionPeriod(String.valueOf(data.getTransactionPeriod()));
        periodicState.setTransactionEndDate(data.getTransactionEndDate().toString());
        periodicState.setSuccessMessage("Period Transaction Recorded successfully!");

        // fire property change
        this.viewModel.setState(periodicState);
        this.viewModel.firePropertyChanged();

        this.viewManager.setActiveViewName(viewModel.getViewName());

        // go back to home page 2
        this.viewManager.changeView("Transaction");
    }

    @Override
    public void prepareFailView(String error) {
        PeriodicTransactionState periodicState = this.viewModel.getState();
        periodicState.setError(error);
        periodicState.setSuccessMessage(null); // Clear success message on failure
        this.viewModel.setState(periodicState);
        this.viewModel.firePropertyChanged();
    }
}
