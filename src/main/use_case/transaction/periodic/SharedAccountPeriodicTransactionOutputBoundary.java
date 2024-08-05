package use_case.transaction.periodic;

import entity.account.SharedAccount;
import use_case.transaction.TransactionOutputBoundary;

import java.time.LocalDate;

/**
 * The SharedAccountPeriodicTransactionOutputBoundary interface provides methods for preparing the view data based on the outcome
 * of a periodic transaction operation involving a shared account.
 * Implementations of this interface will handle the presentation logic for successful and failed periodic transaction attempts
 * for shared accounts.
 */
public interface SharedAccountPeriodicTransactionOutputBoundary extends TransactionOutputBoundary<SharedAccountPeriodicTransactionOutputData> {
    /**
     * Prepares the view data for a successful periodic transaction operation on a shared account.
     *
     * @param outputData the output data of the successful periodic transaction operation
     * @return the view data representing the successful periodic transaction operation
     */
    SharedAccountPeriodicTransactionViewData presentSuccess(SharedAccountPeriodicTransactionOutputData outputData);

    /**
     * Prepares the view data for a failed periodic transaction operation on a shared account.
     *
     * @param outputData the output data of the failed periodic transaction operation
     * @return the view data representing the failed periodic transaction operation
     */
    SharedAccountPeriodicTransactionViewData presentFailure(SharedAccountPeriodicTransactionOutputData outputData);
}


public class SharedAccountPeriodicTransactionViewData {
    private SharedAccount sharedAccount;
    private double transactionAmount;
    private LocalDate transactionStartDate;
    private LocalDate transactionEndDate;
    private int transactionPeriod;
    private String transactionDescription;
    private LocalDate transactionDate;
    private double newBalance;

    /**
     * Constructs a SharedAccountPeriodicTransactionViewData object with the specified details.
     *
     * @param sharedAccount           the shared account for the transaction
     * @param transactionAmount       the amount of the transaction
     * @param transactionStartDate    the start date of the transaction
     * @param transactionEndDate      the end date of the transaction
     * @param transactionPeriod       the period of the transaction
     * @param transactionDescription  the description of the transaction
     * @param transactionDate         the date of the transaction
     * @param newBalance              the new balance after the transaction
     */
    public SharedAccountPeriodicTransactionViewData(SharedAccount sharedAccount, double transactionAmount, LocalDate transactionStartDate, LocalDate transactionEndDate,
                                                    int transactionPeriod, String transactionDescription, LocalDate transactionDate, double newBalance) {
        this.sharedAccount = sharedAccount;
        this.transactionAmount = transactionAmount;
        this.transactionStartDate = transactionStartDate;
        this.transactionEndDate = transactionEndDate;
        this.transactionPeriod = transactionPeriod;
        this.transactionDescription = transactionDescription;
        this.transactionDate = transactionDate;
        this.newBalance = newBalance;
    }

}