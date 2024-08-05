package use_case.transaction.periodic;

import entity.account.SharedAccount;

/**
 * The SharedAccountPeriodicTransactionOutputBoundary interface provides methods for preparing the view data based on the outcome
 * of a periodic transaction operation involving a shared account.
 */
public interface SharedAccountPeriodicTransactionOutputBoundary extends TransactionOutputBoundary<SharedAccountPeriodicTransactionOutputData> {
    /**
     * Prepares the view data for a successful periodic transaction operation on a shared account.
     *
     * @param outputData the output data of the successful periodic transaction operation
     * @return the view data representing the successful periodic transaction operation
     */
    SharedAccountPeriodicTransactionViewData presentSuccess(SharedAccountPeriodicTransactionOutputData outputData);

    /**
     * Prepares the view data for a failed periodic transaction operation on a shared account.
     *
     * @param outputData the output data of the failed periodic transaction operation
     * @return the view data representing the failed periodic transaction operation
     */
    SharedAccountPeriodicTransactionViewData presentFailure(SharedAccountPeriodicTransactionOutputData outputData);
}