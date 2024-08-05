package use_case.transaction.periodic;

import entity.account.SharedAccount;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.TransactionInputBoundary;

/**
 * The SharedAccountPeriodicTransactionInputBoundary interface provides a method for executing periodic transaction operations
 * that involve a shared account.
 * Implementations of this interface will handle the process of creating a periodic transaction using the provided input data.
 *
 */
public interface SharedAccountPeriodicTransactionInputBoundary extends TransactionInputBoundary<SharedAccountPeriodicTransactionInputData> {
    /**
     * Executes the periodic transaction operation for a shared account.
     *
     * @param inputData the input data for the periodic transaction operation
     * @return the output data of the periodic transaction operation
     */
    SharedAccountPeriodicTransactionOutputData execute(SharedAccountPeriodicTransactionInputData inputData);
}