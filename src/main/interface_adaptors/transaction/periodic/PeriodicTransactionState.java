package interface_adaptors.transaction.periodic;

import interface_adaptors.transaction.TransactionState;

public abstract class PeriodicTransactionState extends TransactionState {
    protected String transactionStartDate;
    protected String transactionEndDate;
    protected String transactionPeriod;

    public PeriodicTransactionState() {
        super();
        this.transactionStartDate = "";
        this.transactionEndDate = "";
        this.transactionPeriod = "";
    }

    /**
     * Gets the transaction start date.
     *
     * @return the transaction start date
     */
    public String getTransactionStartDate() {
        return this.transactionStartDate;
    }


    /**
     * Sets the transaction start date.
     *
     * @param transactionStartDate the new transaction start date
     */
    public void setTransactionStartDate(String transactionStartDate) {
        this.transactionStartDate = transactionStartDate;
    }

    /**
     * Gets the transaction end date.
     *
     * @return the transaction end date
     */
    public String getTransactionEndDate() {
        return this.transactionEndDate;
    }

    /**
     * Sets the transaction end date.
     *
     * @param transactionEndDate the new transaction end date
     */
    public void setTransactionEndDate(String transactionEndDate) {
        this.transactionEndDate = transactionEndDate;
    }

    /**
     * Gets the transaction period.
     *
     * @return the transaction period
     */
    public String getTransactionPeriod() {
        return this.transactionPeriod;
    }

    /**
     * Sets the transaction period.
     *
     * @param transactionPeriod the new transaction period
     */
    public void setTransactionPeriod(String transactionPeriod) {
        this.transactionPeriod = transactionPeriod;
    }
}
