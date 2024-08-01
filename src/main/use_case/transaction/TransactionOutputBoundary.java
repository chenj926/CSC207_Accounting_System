package use_case.transaction;

public interface TransactionOutputBoundary<TransactionOutputData>{
    /**
     * Prepares the success view with the given transaction output data.
     *
     * @param transactions the transaction output data containing transaction information and new balance
     */
    void prepareSuccessView(TransactionOutputData transactions);

    /**
     * Prepares the fail view with the given error message.
     *
     * @param error the error message to be presented in case of a failed transaction attempt
     */
    void prepareFailView(String error);
}