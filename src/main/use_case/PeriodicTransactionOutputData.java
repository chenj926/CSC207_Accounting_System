package use_case;

import entity.PeriodicInflow;
import entity.PeriodicOutflow;

import java.time.LocalDate;

/**
 * The PeriodicTransactionOutputData class represents the output data of a periodic transaction operation.
 * It includes details such as the transaction amount, start date, end date, period, description, date, and new balance.
 *
 * @author Eric
 * @author Dana
 * @author Jessica
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
}