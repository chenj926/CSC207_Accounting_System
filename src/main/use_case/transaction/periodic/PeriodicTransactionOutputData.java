package use_case.transaction.periodic;

import entity.transaction.Transaction;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.TransactionOutputData;

import java.time.LocalDate;

public class PeriodicTransactionOutputData extends TransactionOutputData<Transaction> {
    protected LocalDate transactionStartDate;
    protected LocalDate transactionEndDate;
    protected String transactionPeriod;

    public PeriodicTransactionOutputData(PeriodicTransaction periodicTransaction) {
        super(periodicTransaction);
        this.transactionStartDate = periodicTransaction.getStartDate();
        this.transactionEndDate = periodicTransaction.getEndDate();
        this.transactionPeriod = periodicTransaction.getPeriod();
    }

    /**
     * Gets the start date of the transaction.
     *
     * @return the start date of the transaction
     */
    public LocalDate getTransactionStartDate() {
        return this.transactionStartDate;
    }

    /**
     * Gets the end date of the transaction.
     *
     * @return the end date of the transaction
     */
    public LocalDate getTransactionEndDate() {
        return this.transactionEndDate;
    }

    /**
     * Gets the period of the transaction.
     *
     * @return the period of the transaction
     */
    public String getTransactionPeriod() {
        return this.transactionPeriod;
    }
}
