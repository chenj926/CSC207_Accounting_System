package interface_adaptors.transaction.periodic;

import use_case.transaction.periodic.PeriodicTransactionInputBoundary;
import use_case.transaction.periodic.PeriodicTransactionInputData;

/**
 * The PeriodicTransactionController class is responsible for handling user interactions related to periodic transactions.
 * It communicates with the use case interactor to execute the periodic transaction process.
 *
 * @author Xile
 * @author Eric
 */
public class PeriodicTransactionController {

    final PeriodicTransactionInputBoundary periodicTransactionInputBoundary;
    final PeriodicTransactionViewModel viewModel;

    /**
     * Constructs a PeriodicTransactionController object with the specified use case interactor and view model.
     *
     * @param periodicTransactionInputBoundary the use case interactor for periodic transactions
     * @param viewModel                        the view model to update the transaction state
     */
    public PeriodicTransactionController(PeriodicTransactionInputBoundary periodicTransactionInputBoundary,
                                         PeriodicTransactionViewModel viewModel) {
        this.periodicTransactionInputBoundary = periodicTransactionInputBoundary;
        this.viewModel = viewModel;
    }

    /**
     * Executes the periodic transaction process with the given transaction details.
     *
     * @param amount      the amount of the transaction
     * @param startDate   the start date of the transaction
     * @param description the description of the transaction
     * @param period      the period of the transaction
     * @param endDate     the end date of the transaction
     */
    public void execute(String amount, String startDate, String description, String period, String endDate) {
        PeriodicTransactionInputData periodicTransactionInputData = new PeriodicTransactionInputData(
                amount, startDate, description, period, endDate
        );
        periodicTransactionInputBoundary.execute(periodicTransactionInputData);
        this.viewModel.resetState();
    }
}

