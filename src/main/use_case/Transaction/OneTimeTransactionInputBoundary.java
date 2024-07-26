package use_case.Transaction;

/**
 * The OneTimeTransactionInputBoundary interface provides a method for executing one-time transaction operations.
 * Implementations of this interface will handle the process of creating a one-time transaction using the provided input data.
 *
 * @author Dana
 */
public interface OneTimeTransactionInputBoundary {

    /**
     * Executes the one-time transaction process with the given input data.
     *
     * @param oneTimeTransactionInputData the input data required for the one-time transaction process
     */
    void execute(OneTimeTransactionInputData oneTimeTransactionInputData);
}