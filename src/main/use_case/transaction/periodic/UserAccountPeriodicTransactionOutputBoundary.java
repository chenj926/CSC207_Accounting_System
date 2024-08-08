package use_case.transaction.periodic;

/**
 * The UserAccountPeriodicTransactionOutputBoundary interface provides methods for preparing the view based on the outcome of a periodic transaction operation.
 * Implementations of this interface will handle the presentation logic for successful and failed periodic transaction attempts.
 *
 * @author Eric
 * @author Jessica
 */
public interface UserAccountPeriodicTransactionOutputBoundary extends PeriodicTransactionOutputBoundary<UserAccountPeriodicTransactionOutputData>{
    // methods defined in TransactionOutputBoundary interface
    void prepareSuccessView(UserAccountPeriodicTransactionOutputData outputData);
}