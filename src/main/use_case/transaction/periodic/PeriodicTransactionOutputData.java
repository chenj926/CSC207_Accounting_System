package use_case.transaction.periodic;

import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.TransactionOutputData;

import java.time.LocalDate;

/**
 * The PeriodicTransactionOutputData class represents the output data of a periodic transaction operation.
 * It includes details such as the transaction amount, start date, end date, period, description, date, and new balance.
 * This class is used to encapsulate the results of processing periodic transactions, whether they are inflows or outflows.
 * The data captured in this class helps to track and manage the financial transactions over specified periods.
 *
 * <p>This class provides constructors to initialize the output data based on different types of transactions
 * (inflow and outflow), and it offers getter and setter methods for each field, allowing flexible access and modification of the data.</p>
 *
 * @see PeriodicInflow
 * @see PeriodicOutflow
 * @see LocalDate
 *
 * @author Eric
 * @author Dana
 * @author Jessica
 */
public class PeriodicTransactionOutputData extends TransactionOutputData<PeriodicTransaction> {
    private LocalDate transactionStartDate;
    private LocalDate transactionEndDate;
    private int transactionPeriod;
    // private float newBalance;
    private String transactionCategory;

    /**
     * Constructs a PeriodicTransactionOutputData object for an inflow transaction with the specified details.
     *
     * @param periodicTransaction the inflow transaction entity
     */
    public PeriodicTransactionOutputData(PeriodicTransaction periodicTransaction) {
        super(periodicTransaction);
        this.transactionEndDate = periodicTransaction.getEndDate();
        this.transactionPeriod = periodicTransaction.getPeriod();
        this.id = periodicTransaction.getIdentification();
        this.transactionCategory = periodicTransaction.getTransactionCategory();
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
    public int getTransactionPeriod() {
        return this.transactionPeriod;
    }

    /**
     * Sets the start date of the transaction.
     *
     * @param transactionStartDate the new start date of the transaction
     */

    public void setTransactionStartDate(LocalDate transactionStartDate) {
        this. transactionStartDate = transactionStartDate;
    }

    /**
     * Sets the end date of the transaction.
     *
     * @param transactionEndDate the new end date of the transaction
     */
    public void setTransactionEndDate(LocalDate transactionEndDate) {
        this. transactionEndDate = transactionEndDate;
    }

    /**
     * Sets the period of the transaction.
     *
     * @param transactionPeriod the new period of the transaction
     */
    public void setTransactionPeriod(int transactionPeriod) {this.transactionPeriod = transactionPeriod; }
}

