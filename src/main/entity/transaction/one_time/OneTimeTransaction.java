package entity.transaction.one_time;

import entity.transaction.Transaction;

import java.time.LocalDate;

/**
 * The OneTimeTransaction abstract class implements the Transaction interface.
 * It represents a single, non-recurring transaction with additional details such as category and inflow status.
 *
 * @author Xile
 * @author Chi Fong
 * @author Jessica
 * @author Eric
 */
public abstract class OneTimeTransaction implements Transaction {
    private String identification;
    private float amount;
    private LocalDate date;
    private String description;
    private String category;

    private boolean inflow;

    /**
     * Constructs a OneTimeTransaction object with the specified details.
     *
     * @param identification the identification of the transaction
     * @param amount         the amount of the transaction
     * @param date           the date of the transaction
     * @param description    the description of the transaction
     * @param category       the category of the transaction
     */
    public OneTimeTransaction(String identification, float amount, LocalDate date, String description, String category) {
        this.identification = identification;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
        this.inflow = (amount>=0);
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
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    public LocalDate getDate() {
        return this.date;
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
     * Gets the category of the transaction.
     *
     * @return the category of the transaction
     */
    public String getCategory() {return this.category;}

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
     * Sets the date of the transaction.
     *
     * @param date the new date of the transaction
     */
    @Override
    public void setDate(LocalDate date) {
        this.date = date;
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

}
