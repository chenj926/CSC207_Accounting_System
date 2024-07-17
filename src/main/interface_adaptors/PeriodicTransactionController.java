package interface_adaptors;

import use_case.PeriodicTransactionInputBoundary;
import use_case.PeriodicTransactionInputData;

public class PeriodicTransactionController {

    final PeriodicTransactionInputBoundary periodicTransactionInputBoundary;

    public PeriodicTransactionController(PeriodicTransactionInputBoundary periodicTransactionInputBoundary) {
        this.periodicTransactionInputBoundary = periodicTransactionInputBoundary;
    }

    public void execute(String identification, float amount, String startDate, String description, String period, String endDate) {
        PeriodicTransactionInputData periodicTransactionInputData = new PeriodicTransactionInputData(
                identification, amount, startDate, description, period, endDate
        );
        periodicTransactionInputBoundary.execute(periodicTransactionInputData);
    }
}

