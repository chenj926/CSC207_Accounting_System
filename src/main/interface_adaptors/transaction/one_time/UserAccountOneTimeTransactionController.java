package interface_adaptors.transaction.one_time;

import use_case.transaction.one_time.SharedAccountOneTimeTransactionInputBoundary;
import use_case.transaction.one_time.UserAccountOneTimeTransactionInputBoundary;
import use_case.transaction.one_time.UserAccountOneTimeTransactionInputData;

/**
 * The UserAccountOneTimeTransactionController class is responsible for handling user interactions related to one-time transactions.
 * It communicates with the use case interactor to execute the one-time transaction process.
 *
 * @author Xile
 * @author Eric
 */
public class UserAccountOneTimeTransactionController extends
        AccountOneTimeTransactionController<
                UserAccountOneTimeTransactionInputBoundary,
                UserAccountOneTimeTransactionViewModel> {

    /**
     * Constructs a UserAccountOneTimeTransactionController object with the specified use case interactor and view model.
     *
     * @param userOneTimeTransactionUseCaseInteractor the use case interactor for one-time transactions
     * @param viewModel                               the view model to update the transaction state
     */
    public UserAccountOneTimeTransactionController(UserAccountOneTimeTransactionInputBoundary userOneTimeTransactionUseCaseInteractor,
                                                   UserAccountOneTimeTransactionViewModel viewModel) {
        super(userOneTimeTransactionUseCaseInteractor, viewModel);
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
        oneTimeTransactionInteractor.execute(userAccountOneTimeTransactionInputData);
        this.viewModel.resetState();
    }
}
