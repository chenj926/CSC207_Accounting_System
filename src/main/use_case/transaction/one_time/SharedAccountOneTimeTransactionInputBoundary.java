package use_case.transaction.one_time;

import use_case.transaction.TransactionInputBoundary;

/**
 * The SharedAccountOneTimeTransactionInputBoundary interface provides a method for executing one-time transaction operations
 * that involving the shared account.
 * Implementations of this interface will handle the process of creating a one-time transaction using the provided input data,
 * with additional logic for handling shared accounts.
 *
 */
public interface SharedAccountOneTimeTransactionInputBoundary extends OneTimeTransactionInputBoundary<SharedAccountUserAccountOneTimeTransactionInputData> {
}