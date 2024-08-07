package use_case.transaction;

/**
 * The TransactionInputBoundary interface provides a generic method for executing transaction operations.
 * Implementations of this interface will handle the process of creating a transaction using the provided input data.
 *
 * @param <I> the type of the transaction input data
 * @author Jessica
 */
public interface TransactionInputBoundary<I> {
    void execute(I inputData);
    // Optional: add specific methods for shared accounts if needed
}


