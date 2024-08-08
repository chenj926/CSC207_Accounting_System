package use_case.transaction.periodic;

/**
 * The UserAccountPeriodicTransactionInputBoundary interface provides a method for executing periodic transaction operations.
 * Implementations of this interface will handle the process of creating a periodic transaction using the provided input data.
 *
 * @author Eric
 * @author Jessica
 */
public interface UserAccountPeriodicTransactionInputBoundary extends PeriodicTransactionInputBoundary<UserAccountPeriodicTransactionInputData> {
    // execute defined in TransactionInputBoundary interface
}