package use_case.transaction.one_time;

import use_case.transaction.TransactionOutputBoundary;

/**
 * The OneTimeTransactionOutputBoundary interface provides methods for preparing the view based on the outcome of a one-time transaction operation.
 * Implementations of this interface will handle the presentation logic for successful and failed one-time transaction attempts.
 *
 * @author Dana
 * @author Jessica
 */
public interface OneTimeTransactionOutputBoundary extends TransactionOutputBoundary<OneTimeTransactionOutputData>{
    // methods defined in TransactionOutputBoundary interface
}