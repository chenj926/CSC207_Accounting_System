package use_case.transaction.periodic;

import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.TransactionInputBoundary;

/**
 * The PeriodicTransactionInputBoundary interface provides a method for executing periodic transaction operations.
 * Implementations of this interface will handle the process of creating a periodic transaction using the provided input data.
 *
 * @author Eric
 * @author Jessica
 */
public interface PeriodicTransactionInputBoundary extends TransactionInputBoundary<PeriodicTransactionInputData> {
    // execute defined in TransactionInputBoundary interface
}