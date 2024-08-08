package interface_adaptors.transaction.periodic;

public abstract class PeriodicTransactionState {
    protected String id;
    protected String transactionAmount;
    protected String transactionStartDate;
    protected String transactionEndDate;
    protected String transactionPeriod;
    protected String transactionDescription;
    protected String transactionCategory;
    protected String successMessage;
    protected String errorMsg;

    public PeriodicTransactionState() {
        this.id ="";
        this.transactionAmount = "0";
        this.transactionStartDate = "";
        this.transactionEndDate = "";
        this.transactionPeriod = "";
        this.transactionDescription = "";
        this.transactionCategory = "";
        this.errorMsg = null;
        this.successMessage = null;
    }

    /**
     * Gets the transaction amount.
     *
     * @return the transaction amount
     */
    public String getTransactionAmount() {
        return this.transactionAmount;
    }

    /**
     * Sets the transaction amount.
     *
     * @param transactionAmount the new transaction amount
     */
    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
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

    /**
     * Gets the transaction description.
     *
     * @return the transaction description
     */
    public String getTransactionDescription() {
        return this.transactionDescription;
    }

    /**
     * Sets the transaction description.
     *
     * @param transactionDescription the new transaction description
     */
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    /**
     * Gets the success message.
     *
     * @return the success message
     */
    public String getSuccessMessage() {
        return this.successMessage;
    }

    /**
     * Sets the success message.
     *
     * @param successMessage the new success message
     */
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMsg() {
        return this.errorMsg;
    }

    /**
     * Sets the error message.
     *
     * @param error the new error message
     */
    public void setError(String error) {
        this.errorMsg = error;
    }

    public String getTransactionCategory() {
        return this.transactionCategory;
    }

    public void setTransactionCategory(String category) {
        this.transactionCategory = category;
    }

    public void setIdentification (String identification) {
        this.id = identification;
    }
}
