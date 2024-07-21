package interface_adaptors;

import use_case.PeriodicTransactionInputBoundary;
import use_case.PeriodicTransactionInputData;

public class PeriodicTransactionController {

    final PeriodicTransactionInputBoundary periodicTransactionInputBoundary;
    final PeriodicTransactionViewModel viewModel;

    public PeriodicTransactionController(PeriodicTransactionInputBoundary periodicTransactionInputBoundary,
                                         PeriodicTransactionViewModel viewModel) {
        this.periodicTransactionInputBoundary = periodicTransactionInputBoundary;
        this.viewModel = viewModel;
    }

    public void execute(String amount, String startDate, String description, String period, String endDate) {
        PeriodicTransactionInputData periodicTransactionInputData = new PeriodicTransactionInputData(
                amount, startDate, description, period, endDate
        );
        periodicTransactionInputBoundary.execute(periodicTransactionInputData);
        this.viewModel.resetState();
    }
}

