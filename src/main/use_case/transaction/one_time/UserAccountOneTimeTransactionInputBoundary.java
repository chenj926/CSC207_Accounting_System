package use_case.transaction.one_time;

import use_case.transaction.TransactionInputBoundary;

/**
 * The OneTimeTransactionInputBoundary interface provides a method for executing one-time transaction operations.
 * Implementations of this interface will handle the process of creating a one-time transaction using the provided input data.
 *
 * @author Dana
 * @author Jessica
 */
public interface UserAccountOneTimeTransactionInputBoundary extends OneTimeTransactionInputBoundary<UserAccountOneTimeTransactionInputData>{
    // execute defined in TransactionInputBoundary interface
}