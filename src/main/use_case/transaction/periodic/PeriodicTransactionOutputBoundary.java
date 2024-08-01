package use_case.transaction.periodic;

import use_case.transaction.TransactionOutputBoundary;

/**
 * The PeriodicTransactionOutputBoundary interface provides methods for preparing the view based on the outcome of a periodic transaction operation.
 * Implementations of this interface will handle the presentation logic for successful and failed periodic transaction attempts.
 *
 * @author Eric
 * @author Jessica
 */
public interface PeriodicTransactionOutputBoundary extends TransactionOutputBoundary<PeriodicTransactionOutputData>{
    // methods defined in TransactionOutputBoundary interface
}