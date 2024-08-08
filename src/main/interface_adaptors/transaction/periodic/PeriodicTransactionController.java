package interface_adaptors.transaction.periodic;

import use_case.transaction.periodic.UserAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.UserAccountPeriodicTransactionInputData;

/**
 * The PeriodicTransactionController class is responsible for handling user interactions related to periodic transactions.
 * It communicates with the use case interactor to execute the periodic transaction process.
 *
 * @author Xile
 * @author Eric
 */
public class PeriodicTransactionController {

    final UserAccountPeriodicTransactionInputBoundary userAccountPeriodicTransactionInputBoundary;
    final PeriodicTransactionViewModel viewModel;

    /**
     * Constructs a PeriodicTransactionController object with the specified use case interactor and view model.
     *
     * @param userAccountPeriodicTransactionInputBoundary the use case interactor for periodic transactions
     * @param viewModel                        the view model to update the transaction state
     */
    public PeriodicTransactionController(UserAccountPeriodicTransactionInputBoundary userAccountPeriodicTransactionInputBoundary,
                                         PeriodicTransactionViewModel viewModel) {
        this.userAccountPeriodicTransactionInputBoundary = userAccountPeriodicTransactionInputBoundary;
        this.viewModel = viewModel;
    }

    /**
     * Executes the periodic transaction process with the given transaction details.
     *
     * @param id          the identification of the user
     * @param amount      the amount of the transaction
     * @param startDate   the start date of the transaction
     * @param description the description of the transaction
     * @param period      the period of the transaction
     * @param endDate     the end date of the transaction
     */
    public void execute(String id, String amount, String startDate, String description, String period, String endDate, String category, String date) {
        UserAccountPeriodicTransactionInputData userAccountPeriodicTransactionInputData = new UserAccountPeriodicTransactionInputData(id,
                amount, startDate, description, period, endDate, category, date);
        userAccountPeriodicTransactionInputBoundary.execute(userAccountPeriodicTransactionInputData);
        this.viewModel.resetState();
    }
}

