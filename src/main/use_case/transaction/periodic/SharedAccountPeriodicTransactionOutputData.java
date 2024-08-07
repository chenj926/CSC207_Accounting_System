package use_case.transaction.periodic;

import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.TransactionOutputData;
import java.time.LocalDate;
import java.util.Set;

/**
 * The SharedAccountPeriodicTransactionOutputData class represents the output data of a periodic transaction operation
 * that involves a shared account.
 * It includes details such as the transaction amount, start date, end date, period, description, date, new balance,
 * and the set of user IDs responsible for the transaction.
 * This class is used to encapsulate the results of processing periodic transactions for shared accounts,
 * whether they are inflows or outflows.
 *
 * <p>This class provides constructors to initialize the output data based on different types of transactions
 * (inflow and outflow), and it offers getter and setter methods for each field, allowing flexible access and modification of the data.</p>
 *
 * @see PeriodicTransaction
 * @see LocalDate
 *
 * author Eric
 * author Dana
 * author Jessica
 */
public class SharedAccountPeriodicTransactionOutputData extends TransactionOutputData<PeriodicTransaction> {
    private LocalDate transactionStartDate;
    private LocalDate transactionEndDate;
    private String transactionPeriod;
    private Set<String> responsibleUserIds;
    private float newSharedAccountBalance;

    /**
     * Constructs a SharedAccountPeriodicTransactionOutputData object for a periodic transaction with the specified details.
     *
     * @param periodicTransaction      the periodic transaction entity
     * @param responsibleUserIds       the set of user IDs responsible for the transaction
     * @param newSharedAccountBalance  the new balance of the shared account after the transaction
     */
    public SharedAccountPeriodicTransactionOutputData(PeriodicTransaction periodicTransaction,
                                                      Set<String> responsibleUserIds,
                                                      float newSharedAccountBalance) {
        super(periodicTransaction);
        this.transactionEndDate = periodicTransaction.getEndDate();
        this.transactionPeriod = periodicTransaction.getPeriod();
        this.id = periodicTransaction.getIdentification();
        this.transactionCategory = periodicTransaction.getTransactionCategory();
        this.transactionStartDate = periodicTransaction.getStartDate();
        this.responsibleUserIds = responsibleUserIds;
        this.newSharedAccountBalance = newSharedAccountBalance;
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

    /**
     * Sets the start date of the transaction.
     *
     * @param transactionStartDate the new start date of the transaction
     */
    public void setTransactionStartDate(LocalDate transactionStartDate) {
        this.transactionStartDate = transactionStartDate;
    }

    /**
     * Sets the end date of the transaction.
     *
     * @param transactionEndDate the new end date of the transaction
     */
    public void setTransactionEndDate(LocalDate transactionEndDate) {
        this.transactionEndDate = transactionEndDate;
    }

    /**
     * Sets the period of the transaction.
     *
     * @param transactionPeriod the new period of the transaction
     */
    public void setTransactionPeriod(String transactionPeriod) {
        this.transactionPeriod = transactionPeriod;
    }

    /**
     * Gets the set of user IDs responsible for the transaction.
     *
     * @return the set of responsible user IDs
     */
    public Set<String> getResponsibleUserIds() {
        return responsibleUserIds;
    }

    /**
     * Sets the set of user IDs responsible for the transaction.
     *
     * @param responsibleUserIds the set of responsible user IDs
     */
    public void setResponsibleUserIds(Set<String> responsibleUserIds) {
        this.responsibleUserIds = responsibleUserIds;
    }

    /**
     * Gets the new balance of the shared account after the transaction.
     *
     * @return the new balance of the shared account
     */
    public float getNewSharedAccountBalance() {
        return newSharedAccountBalance;
    }

    /**
     * Sets the new balance of the shared account after the transaction.
     *
     * @param newSharedAccountBalance the new balance of the shared account
     */
    public void setNewSharedAccountBalance(float newSharedAccountBalance) {
        this.newSharedAccountBalance = newSharedAccountBalance;
    }
}
