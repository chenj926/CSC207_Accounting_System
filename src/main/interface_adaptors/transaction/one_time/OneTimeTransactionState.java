package interface_adaptors.transaction.one_time;

import interface_adaptors.transaction.TransactionState;

public abstract class OneTimeTransactionState extends TransactionState {
    protected String transactionDate;
    protected float newBalance;
    protected boolean useCaseFailed;

    /**
     * Constructs a UserOneTimeTransactionState object with default values.
     */
    public OneTimeTransactionState() {
        super();
        this.transactionDate = null;
    }

    /**
     * Gets the transaction date.
     *
     * @return the transaction date
     */
    public String getTransactionDate() {
        return this.transactionDate;
    }

    /**
     * Gets the new balance after the transaction.
     *
     * @return the new balance after the transaction
     */
    public float getNewBalance() {
        return this.newBalance;
    }

    /**
     * Checks if the use case failed.
     *
     * @return true if the use case failed, false otherwise
     */
    public boolean isUseCaseFailed() {
        return this.useCaseFailed;
    }

    /**
     * Sets the new balance after the transaction.
     *
     * @param newBalance the new balance after the transaction
     */
    public void setNewBalance(float newBalance) {
        this.newBalance = newBalance;
    }

    /**
     * Sets the use case failure status.
     *
     * @param useCaseFailed the new use case failure status
     */
    public void setUseCaseFailed(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Sets the transaction date.
     *
     * @param transactionDate the new transaction date
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
