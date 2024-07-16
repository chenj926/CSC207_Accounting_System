package interface_adaptors;

import use_case.OneTimeTransactionInputBoundary;
import use_case.OneTimeTransactionInputData;

public class OneTimeTransactionController {

    final OneTimeTransactionInputBoundary userOneTimeTransactionUseCaseInteractor;

    public OneTimeTransactionController(OneTimeTransactionInputBoundary userOneTimeTransactionUseCaseInteractor) {
        this.userOneTimeTransactionUseCaseInteractor = userOneTimeTransactionUseCaseInteractor;
    }

    public void execute(float amount, String identification, String transactionDate, String transactionDescription, String transactionCategory) {
        OneTimeTransactionInputData oneTimeTransactionInputData = new OneTimeTransactionInputData(
                amount, identification, transactionDate, transactionDescription, transactionCategory
        );
        userOneTimeTransactionUseCaseInteractor.execute(oneTimeTransactionInputData);
    }
}
