package interface_adaptors.transaction.periodic.user_account;

import interface_adaptors.transaction.periodic.PeriodicTransactionController;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInputData;

/**
 * The UserAccountPeriodicTransactionController class is responsible for handling user interactions related to periodic transactions.
 * It communicates with the use case interactor to execute the periodic transaction process.
 *
 * @author Xile
 * @author Eric
 */
public class UserAccountPeriodicTransactionController extends PeriodicTransactionController<
        UserAccountPeriodicTransactionInputBoundary,
        UserAccountPeriodicTransactionViewModel> {

    /**
     * Constructs a UserAccountPeriodicTransactionController object with the specified use case interactor and view model.
     *
     * @param userAccountPeriodicTransactionInputBoundary the use case interactor for periodic transactions
     * @param viewModel                        the view model to update the transaction state
     */
    public UserAccountPeriodicTransactionController(UserAccountPeriodicTransactionInputBoundary userAccountPeriodicTransactionInputBoundary,
                                                    UserAccountPeriodicTransactionViewModel viewModel) {
        super(userAccountPeriodicTransactionInputBoundary, viewModel);
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
        this.transactionInputBoundary.execute(userAccountPeriodicTransactionInputData);
        this.viewModel.resetState();
    }
}

