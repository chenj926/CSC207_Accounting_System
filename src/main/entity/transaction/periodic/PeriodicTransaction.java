package entity.transaction.periodic;

import entity.transaction.Transaction;

import java.time.LocalDate;

/**
 * The PeriodicTransaction abstract class implements the Transaction interface.
 * It represents a recurring transaction with additional details such as start date, end date, period, and inflow status.
 *
 * @author Xile
 * @author Chi Fong
 * @author Dana
 * @author Jessica
 * @author Eric
 */
public abstract class PeriodicTransaction implements Transaction {
    private String identification;
    private float amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate date;
    private int period;
    private String description;
    private String transactionCategory;

    private boolean inflow;

    /**
     * Constructs a PeriodicTransaction object with the specified details.
     *
     * @param identification the identification of the transaction
     * @param amount         the amount of the transaction
     * @param startDate      the start date of the transaction
     * @param description    the description of the transaction
     * @param endDate        the end date of the transaction
     * @param period         the period of the transaction in days
     * @param transactionCategory         the category of the transaction
     */
    public PeriodicTransaction(String identification, float amount, LocalDate startDate, String description,
                               LocalDate endDate, int period, String transactionCategory) {
        this.identification = identification;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = period;
        this.description = description;
        this.inflow = (amount>=0);
        this.date = startDate;
        this.transactionCategory = "";
    }

    /**
     * Gets the identification of the transaction.
     *
     * @return the identification of the transaction
     */
    @Override
    public String getIdentification() {
        return this.identification;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return the amount of the transaction
     */
    @Override
    public float getAmount() {
        return this.amount;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return the description of the transaction
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the transaction is an inflow.
     *
     * @return true if the transaction is an inflow, false otherwise
     */
    public boolean isInflow() {
        return this.inflow;
    }

    /**
     * Gets the start date of the transaction.
     *
     * @return the start date of the transaction
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the end date of the transaction.
     *
     * @return the end date of the transaction
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Gets the period of the transaction in days.
     *
     * @return the period of the transaction
     */
    public int getPeriod() {
        return this.period;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }


    public String getTransactionCategory(){
        return this.transactionCategory;
    }

    /**
     * Sets the identification of the transaction.
     *
     * @param identification the new identification of the transaction
     */
    @Override
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Sets the amount of the transaction and updates the inflow status.
     *
     * @param amount the new amount of the transaction
     */
    @Override
    public void setAmount(float amount) {
        this.amount = amount;
        this.inflow = (amount>=0);
    }

    /**
     * Sets the start date of the transaction.
     *
     * @param startDate the new start date of the transaction
     */
    public void setStartDateDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the end date of the transaction.
     *
     * @param endDate the new end date of the transaction
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the period of the transaction in days.
     *
     * @param period the new period of the transaction
     */
    public void setPeriod(int period) {
        this.period = period;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param description the new description of the transaction
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the date of the transaction.
     *
     * @param date the new date of the transaction
     */
    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the category of the transaction.
     *
     * @param transactionCategory of the transaction
     */
    public void setTransactionCategory(String transactionCategory){this.transactionCategory = transactionCategory;}
}