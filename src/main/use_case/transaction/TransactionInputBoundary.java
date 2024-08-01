package use_case.transaction;

/**
 * The TransactionInputBoundary interface provides a generic method for executing transaction operations.
 * Implementations of this interface will handle the process of creating a transaction using the provided input data.
 *
 * @param <TransactionInputData> the type of the transaction input data
 */
public interface TransactionInputBoundary<TransactionInputData> {

    /**
     * Executes the transaction process with the given input data.
     *
     * @param inputData the input data required for the transaction process
     */
    void execute(TransactionInputData inputData);
}

