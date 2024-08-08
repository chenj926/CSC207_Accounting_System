package use_case.transaction.periodic;

/**
 * The SharedAccountPeriodicTransactionOutputBoundary interface provides methods for preparing the view data based on the outcome
 * of a periodic transaction operation involving a shared account.
 */
public interface SharedAccountPeriodicTransactionOutputBoundary extends PeriodicTransactionOutputBoundary<SharedAccountPeriodicTransactionOutputData> {
    void prepareSuccessView(SharedAccountPeriodicTransactionOutputData outputData);
}