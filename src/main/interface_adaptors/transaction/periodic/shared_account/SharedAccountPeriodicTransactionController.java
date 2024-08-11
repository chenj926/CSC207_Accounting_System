package interface_adaptors.transaction.periodic.shared_account;

import interface_adaptors.transaction.periodic.PeriodicTransactionController;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionInputData;

import java.util.Set;

/**
 * The SharedAccountPeriodicTransactionController class is responsible for handling user interactions related to shared account periodic transactions.
 * It communicates with the use case interactor to execute the shared account periodic transaction process.
 * This includes managing the input data, such as transaction details and responsible user IDs, and updating the view model state.
 *
 * @see SharedAccountPeriodicTransactionInputBoundary
 * @see SharedAccountPeriodicTransactionViewModel
 * @see SharedAccountPeriodicTransactionInputData
 * @see Set
 *
 *
 */
public class SharedAccountPeriodicTransactionController extends PeriodicTransactionController<
        SharedAccountPeriodicTransactionInputBoundary,
        SharedAccountPeriodicTransactionViewModel> {


    /**
     * Constructs a SharedAccountPeriodicTransactionController object with the specified use case interactor and view model.
     *
     * @param sharedAccountPeriodicTransactionInputBoundary the use case interactor for shared account periodic transactions
     * @param viewModel                                     the view model to update the shared account transaction state
     */
    public SharedAccountPeriodicTransactionController( SharedAccountPeriodicTransactionInputBoundary sharedAccountPeriodicTransactionInputBoundary,
            SharedAccountPeriodicTransactionViewModel viewModel) {
        super(sharedAccountPeriodicTransactionInputBoundary, viewModel);
    }

    public void execute(String amount, String startDate, String description, String period, String endDate,
                        String category, String transactionDate, String sharedAccountId, String userId) {
        // Create input data object for the transaction
        SharedAccountPeriodicTransactionInputData inputData = new SharedAccountPeriodicTransactionInputData(
                amount, startDate, endDate, period, description, category, transactionDate, sharedAccountId, userId
        );

        // Execute the transaction process using the interactor
        periodicTransactionInputBoundary.execute(inputData);
        this.viewModel.resetState();
    }
}

