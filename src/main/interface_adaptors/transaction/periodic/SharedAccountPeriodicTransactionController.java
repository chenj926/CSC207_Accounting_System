package interface_adaptors.transaction.periodic;

import use_case.transaction.periodic.SharedAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.SharedAccountUserAccountPeriodicTransactionInputData;

import java.util.Set;

/**
 * The SharedAccountPeriodicTransactionController class is responsible for handling user interactions related to shared account periodic transactions.
 * It communicates with the use case interactor to execute the shared account periodic transaction process.
 * This includes managing the input data, such as transaction details and responsible user IDs, and updating the view model state.
 *
 * @see SharedAccountPeriodicTransactionInputBoundary
 * @see SharedAccountPeriodicTransactionViewModel
 * @see SharedAccountUserAccountPeriodicTransactionInputData
 * @see Set
 *
 *
 */
public class SharedAccountPeriodicTransactionController {

    final SharedAccountPeriodicTransactionInputBoundary sharedAccountPeriodicTransactionInputBoundary;
    final SharedAccountPeriodicTransactionViewModel viewModel;

    /**
     * Constructs a SharedAccountPeriodicTransactionController object with the specified use case interactor and view model.
     *
     * @param sharedAccountPeriodicTransactionInputBoundary the use case interactor for shared account periodic transactions
     * @param viewModel                                     the view model to update the shared account transaction state
     */
    public SharedAccountPeriodicTransactionController(
            SharedAccountPeriodicTransactionInputBoundary sharedAccountPeriodicTransactionInputBoundary,
            SharedAccountPeriodicTransactionViewModel viewModel) {
        this.sharedAccountPeriodicTransactionInputBoundary = sharedAccountPeriodicTransactionInputBoundary;
        this.viewModel = viewModel;
    }

    public void execute(String amount, String startDate, String description, String period, String endDate,
                        String category, String transactionDate, String sharedAccountId, String userId) {
        // Create input data object for the transaction
        SharedAccountUserAccountPeriodicTransactionInputData inputData = new SharedAccountUserAccountPeriodicTransactionInputData(
                amount, startDate, description, period, endDate, category, transactionDate, sharedAccountId, userId
        );

        // Execute the transaction process using the interactor
        sharedAccountPeriodicTransactionInputBoundary.execute(inputData);

        // Reset the view model state after processing the transaction
        this.viewModel.resetState();
    }
}

