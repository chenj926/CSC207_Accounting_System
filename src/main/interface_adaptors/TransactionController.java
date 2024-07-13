package interface_adapter;

import use_case.TransactionInputBoundary;
import use_case.TransactionInputData;

public class TransacationController {

    final TransacationBoundary userTransacationUseCaseInteractor;
    public TransacationController(TransacationInputBoundary userTransactionUseCaseInteractor) {
        this.userTransacationUseCaseInteractor = userTransactionUseCaseInteractor;
    }

    public void execute(float amount) {
        TransacationInputData transacationInputData = new TransacationInputData(
                amount);

        userTransacationUseCaseInteractor.execute(transacationInputData);
    }
}