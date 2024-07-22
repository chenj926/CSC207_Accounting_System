package use_case;

/**
 * The OneTimeTransactionOutputBoundary interface provides methods for preparing the view based on the outcome of a one-time transaction operation.
 * Implementations of this interface will handle the presentation logic for successful and failed one-time transaction attempts.
 *
 * @author Dana
 */
public interface OneTimeTransactionOutputBoundary{
    /**
     * Prepares the success view with the given one-time transaction output data.
     *
     * @param transactions the one-time transaction output data containing transaction information and new balance
     */
    void prepareSuccessView(OneTimeTransactionOutputData transactions);

    /**
     * Prepares the fail view with the given error message.
     *
     * @param error the error message to be presented in case of a failed one-time transaction attempt
     */
    void prepareFailView(String error);
}