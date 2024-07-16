package interface_adaptors;

import use_case.OneTimeTransactionInputBoundary;
import use_case.OneTimeTransactionInputData;


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