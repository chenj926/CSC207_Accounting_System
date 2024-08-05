package use_case.transaction.one_time;

import use_case.transaction.TransactionInputBoundary;

/**
 * The SharedAccountOneTimeTransactionInputBoundary interface provides a method for executing one-time transaction operations
 * that involving the shared account.
 * Implementations of this interface will handle the process of creating a one-time transaction using the provided input data,
 * with additional logic for handling shared accounts.
 *
 */
public interface SharedAccountOneTimeTransactionInputBoundary extends TransactionInputBoundary<SharedAccountOneTimeTransactionInputData> {
    /**
     * Executes the one-time transaction operation for a shared account.
     *
     * @param inputData the input data for the one-time transaction operation
     * @return the result of the one-time transaction operation
     */
    Object execute(SharedAccountOneTimeTransactionInputData inputData);
}