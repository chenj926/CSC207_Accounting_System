package use_case.transaction.periodic.shared_account;

import use_case.transaction.periodic.PeriodicTransactionInputBoundary;

/**
 * The SharedAccountPeriodicTransactionInputBoundary interface provides a method for executing periodic transaction operations
 * that involve a shared account.
 * Implementations of this interface will handle the process of creating a periodic transaction using the provided input data.
 *
 */
public interface SharedAccountPeriodicTransactionInputBoundary extends PeriodicTransactionInputBoundary<SharedAccountPeriodicTransactionInputData> {
}