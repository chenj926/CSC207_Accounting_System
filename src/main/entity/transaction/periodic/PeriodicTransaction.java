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
public abstract class PeriodicTransaction extends Transaction {

    private LocalDate startDate;
    private LocalDate endDate;
    private int period;

    /**
     * Constructs a PeriodicTransaction object with the specified details.
     *
     * @param identification the identification of the transaction
     * @param amount         the amount of the transaction
     * @param startDate      the start date of the transaction
     * @param description    the description of the transaction
     * @param endDate        the end date of the transaction
     * @param period         the period of the transaction in days
     */
    public PeriodicTransaction(String identification, float amount, LocalDate startDate, String description,
                               LocalDate endDate, int period) {
        this.identification = identification;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = period;
        this.description = description;
        this.inflow = (amount>=0);
        this.date = startDate;
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
     * Sets the start date of the transaction.
     *
     * @param startDate the new start date of the transaction
     */
    public void setStartDate(LocalDate startDate) {
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
}