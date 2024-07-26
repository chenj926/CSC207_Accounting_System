package use_case.Transaction;

/**
 * The PeriodicTransactionInputBoundary interface provides a method for executing periodic transaction operations.
 * Implementations of this interface will handle the process of creating a periodic transaction using the provided input data.
 *
 * @author Eric
 */
public interface PeriodicTransactionInputBoundary {

    /**
     * The PeriodicTransactionInputBoundary interface provides a method for executing periodic transaction operations.
     * Implementations of this interface will handle the process of creating a periodic transaction using the provided input data.
     */
    void execute(PeriodicTransactionInputData periodicTransactionInputData);
}