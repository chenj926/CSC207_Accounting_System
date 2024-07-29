package use_case.transaction.periodic;

/**
 * The PeriodicTransactionOutputBoundary interface provides methods for preparing the view based on the outcome of a periodic transaction operation.
 * Implementations of this interface will handle the presentation logic for successful and failed periodic transaction attempts.
 *
 * @author Eric
 */
public interface PeriodicTransactionOutputBoundary {

    /**
     * Prepares the success view with the given periodic transaction output data.
     *
     * @param transactions the periodic transaction output data containing transaction information and new balance
     */
    void prepareSuccessView(PeriodicTransactionOutputData transactions);

    /**
     * Prepares the fail view with the given error message.
     *
     * @param err the error message to be presented in case of a failed periodic transaction attempt
     */
    void prepareFailView(String err);
}