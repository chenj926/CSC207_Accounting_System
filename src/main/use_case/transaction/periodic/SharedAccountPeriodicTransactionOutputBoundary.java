package use_case.transaction.periodic;

import entity.account.SharedAccount;
import use_case.transaction.TransactionOutputBoundary;

/**
 * The SharedAccountPeriodicTransactionOutputBoundary interface provides methods for preparing the view based on the outcome
 * of a periodic transaction operation involving a shared account.
 * Implementations of this interface will handle the presentation logic for successful and failed periodic transaction attempts
 * for shared accounts.
 */
public interface SharedAccountPeriodicTransactionOutputBoundary extends TransactionOutputBoundary<SharedAccountPeriodicTransactionOutputData> {
    /**
     * Prepares the view for a successful periodic transaction operation on a shared account.
     *
     * @param outputData the output data of the successful periodic transaction operation
     * @return the view data representing the successful periodic transaction operation
     */
    SharedAccountPeriodicTransactionViewModel presentSuccess(SharedAccountPeriodicTransactionOutputData outputData);

    /**
     * Prepares the view for a failed periodic transaction operation on a shared account.
     *
     * @param outputData the output data of the failed periodic transaction operation
     * @return the view data representing the failed periodic transaction operation
     */
    SharedAccountPeriodicTransactionViewModel presentFailure(SharedAccountPeriodicTransactionOutputData outputData);
}