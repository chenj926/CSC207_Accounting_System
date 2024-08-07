package interface_adaptors.transaction.one_time;

import use_case.transaction.one_time.UserAccountOneTimeTransactionInputBoundary;
import use_case.transaction.one_time.UserAccountOneTimeTransactionInputData;

/**
 * The OneTimeTransactionController class is responsible for handling user interactions related to one-time transactions.
 * It communicates with the use case interactor to execute the one-time transaction process.
 *
 * @author Xile
 * @author Eric
 */
public class OneTimeTransactionController {

    final UserAccountOneTimeTransactionInputBoundary userOneTimeTransactionUseCaseInteractor;
    final OneTimeTransactionViewModel viewModel;

    /**
     * Constructs a OneTimeTransactionController object with the specified use case interactor and view model.
     *
     * @param userOneTimeTransactionUseCaseInteractor the use case interactor for one-time transactions
     * @param viewModel                               the view model to update the transaction state
     */
    public OneTimeTransactionController(UserAccountOneTimeTransactionInputBoundary userOneTimeTransactionUseCaseInteractor,
                                        OneTimeTransactionViewModel viewModel) {
        this.userOneTimeTransactionUseCaseInteractor = userOneTimeTransactionUseCaseInteractor;
        this.viewModel = viewModel;
    }

    /**
     * Executes the one-time transaction process with the given transaction details.
     *
     * @param amount               the amount of the transaction
     * @param transactionDate      the date of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory  the category of the transaction
     */
    public void execute(String id, String amount, String transactionDate, String transactionDescription,
                        String transactionCategory) {
        UserAccountOneTimeTransactionInputData userAccountOneTimeTransactionInputData = new UserAccountOneTimeTransactionInputData(
                id, amount, transactionDate, transactionDescription, transactionCategory
        );
        userOneTimeTransactionUseCaseInteractor.execute(userAccountOneTimeTransactionInputData);
        this.viewModel.resetState();
    }
}
