package use_case.transaction.periodic;

import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;

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
 * <p>The class is authored by Eric, Dana, and Jessica.</p>
 *
 * @see PeriodicInflow
 * @see PeriodicOutflow
 * @see LocalDate
 *
 */
public class PeriodicTransactionOutputData {
    private float transactionAmount;
    private String id;
    private LocalDate transactionStartDate;
    private LocalDate transactionEndDate;
    private int transactionPeriod;
    private String transactionDescription;
    private LocalDate date;
    private float newBalance;
    private String transactionCategory;

    /**
     * Constructs a PeriodicTransactionOutputData object for an inflow transaction with the specified details.
     *
     * @param periodicInflow the inflow transaction entity
     * @param newBalance     the new balance after the transaction
     */
    public PeriodicTransactionOutputData(PeriodicInflow periodicInflow, float newBalance) {

        this.transactionAmount = periodicInflow.getAmount();
        this.transactionStartDate = periodicInflow.getStartDate();
        this.transactionDescription = periodicInflow.getDescription();
        this.transactionEndDate = periodicInflow.getEndDate();
        this.transactionPeriod = periodicInflow.getPeriod();
        this.id = periodicInflow.getIdentification();
        this.newBalance = newBalance;
        this.transactionCategory = periodicInflow.getTransactionCategory();
    }

    /**
     * Constructs a PeriodicTransactionOutputData object for an outflow transaction with the specified details.
     *
     * @param periodicOutflow the outflow transaction entity
     * @param newBalance      the new balance after the transaction
     */
    public PeriodicTransactionOutputData(PeriodicOutflow periodicOutflow, float newBalance) {

        this.transactionAmount = periodicOutflow.getAmount();
        this.transactionStartDate = periodicOutflow.getStartDate();
        this.transactionDescription = periodicOutflow.getDescription();
        this.transactionEndDate = periodicOutflow.getEndDate();
        this.transactionPeriod = periodicOutflow.getPeriod();
        this.id = periodicOutflow.getIdentification();
        this.newBalance = newBalance;
        this.transactionCategory = periodicOutflow.getTransactionCategory();
    }

    /**
     * Gets the transaction amount.
     *
     * @return the transaction amount
     */
    public float getTransactionAmount() {
        return this.transactionAmount;
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
     * Gets the description of the transaction.
     *
     * @return the description of the transaction
     */
    public String getTransactionDescription() {
        return transactionDescription;
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
     * Gets the current date.
     *
     * @return the current date
     */
    public LocalDate getDate() {return this.date; }

    /**
     * Gets the new balance after the transaction.
     *
     * @return the new balance after the transaction
     */
    public float getNewBalance() {return this.newBalance;}

    /**
     * Gets the identification of the transaction.
     *
     * @return the identification of the transaction
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the start date of the transaction.
     *
     * @param transactionStartDate the new start date of the transaction
     */

    public void setTransactionStartDate(LocalDate transactionStartDate) {
        this. transactionStartDate = transactionStartDate;
    }

//    /**
//     * Sets the category of the transaction.
//     *
//     * @param transactionCategory of the transaction
//     */
//    public String getTransactionCategory() {
//        return this.transactionCategory;
//    }

    /**
     * Sets the end date of the transaction.
     *
     * @param transactionEndDate the new end date of the transaction
     */
    public void setTransactionEndDate(LocalDate transactionEndDate) {
        this. transactionEndDate = transactionEndDate;
    }

    /**
     * Sets the transaction amount.
     *
     * @param transactionAmount the new transaction amount
     */
    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param transactionDescription the new description of the transaction
     */
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    /**
     * Sets the period of the transaction.
     *
     * @param transactionPeriod the new period of the transaction
     */
    public void setTransactionPeriod(int transactionPeriod) {this.transactionPeriod = transactionPeriod; }

    /**
     * Sets the current date.
     *
     * @param date the new date
     */
    public void setDate (LocalDate date) { this.date = date;}

    /**
     * Sets the new balance after the transaction.
     *
     * @param newBalance the new balance after the transaction
     */
    public void setNewBalance(float newBalance) {this.newBalance = newBalance;}

    /**
     * Sets the new category of the transaction.
     *
     * @param transactionCategory the new category of the transaction
     */
    public void setTransactionCategory(String transactionCategory) {this.transactionCategory = transactionCategory;}
}

