package use_case.transaction.one_time;

import use_case.transaction.TransactionOutputBoundary;

/**
 * The SharedAccountOneTimeTransactionOutputBoundary interface provides methods for preparing the view based on the outcome
 * of a one-time transaction operation that involves a shared account.
 * Implementations of this interface will handle the presentation logic for successful and failed one-time transaction
 * attempts for shared accounts.
 *
 */
public interface SharedAccountOneTimeTransactionOutputBoundary extends OneTimeTransactionOutputBoundary<SharedAccountOneTimeTransactionOutputData> {
    /**
     * Prepares the success view with the given transaction output data.
     *
     * @param outputData the transaction output data containing transaction information and new balance
     */
    void prepareSuccessView(OneTimeTransactionOutputData outputData);
}