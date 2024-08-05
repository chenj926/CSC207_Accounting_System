package use_case.transaction.one_time;

import use_case.transaction.TransactionOutputBoundary;

/**
 * The SharedAccountOneTimeTransactionOutputBoundary interface provides methods for preparing the view based on the outcome
 * of a one-time transaction operation that involves a shared account.
 * Implementations of this interface will handle the presentation logic for successful and failed one-time transaction
 * attempts for shared accounts.
 *
 */
public interface SharedAccountOneTimeTransactionOutputBoundary extends TransactionOutputBoundary<SharedAccountOneTimeTransactionOutputData> {
    /**
     * Prepares the view for a successful one-time transaction operation for a shared account.
     *
     * @param outputData the output data for the successful one-time transaction operation
     * @return the view representation of the successful one-time transaction operation
     */
    Object onSuccess(SharedAccountOneTimeTransactionOutputData outputData);

    /**
     * Prepares the view for a failed one-time transaction operation for a shared account.
     *
     * @param outputData the output data for the failed one-time transaction operation
     * @return the view representation of the failed one-time transaction operation
     */
    Object onFailure(SharedAccountOneTimeTransactionOutputData outputData);
}