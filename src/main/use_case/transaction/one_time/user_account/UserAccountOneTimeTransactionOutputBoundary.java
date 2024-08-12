package use_case.transaction.one_time.user_account;

import use_case.transaction.one_time.OneTimeTransactionOutputBoundary;

/**
 * The UserAccountOneTimeTransactionOutputBoundary interface provides methods for preparing the view based on the outcome of a one-time transaction operation.
 * Implementations of this interface will handle the presentation logic for successful and failed one-time transaction attempts.
 *
 * @author Dana
 * @author Jessica
 */
public interface UserAccountOneTimeTransactionOutputBoundary extends OneTimeTransactionOutputBoundary<UserAccountOneTimeTransactionOutputData> {
    // methods defined in TransactionOutputBoundary interface
    /**
     * Prepares the success view with the given transaction output data.
     *
     * @param outputData the transaction output data containing transaction information and new balance
     */
    void prepareSuccessView(UserAccountOneTimeTransactionOutputData outputData);
}