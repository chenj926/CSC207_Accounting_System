package interface_adaptors.transaction.one_time;

/**
 * The OneTimeTransactionState class represents the state of a one-time transaction,
 * including transaction details, new balance, and success or error messages.
 *
 * @author Xile
 * @author Eric
 */
public class OneTimeTransactionState {
    private String id;
    private String transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String transactionCategory;
    private float newBalance;
    private boolean useCaseFailed;
    private String errorMessage;
    private String successMessage;

    /**
     * Constructs a OneTimeTransactionState object with default values.
     */
    public OneTimeTransactionState() {
        this.id = "";
        this.transactionAmount = "0";
        this.transactionDate = null;
        this.transactionDescription = "";
        this.transactionCategory = "";
        this.errorMessage = null;
        this.successMessage = null;
    }

    /**
     * Gets the transaction ID.
     *
     * @return the transaction ID
     */
    public String getId() {
        return this.id;
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
     * Gets the transaction date.
     *
     * @return the transaction date
     */
    public String getTransactionDate() {
        return this.transactionDate;
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
     * Gets the transaction category.
     *
     * @return the transaction category
     */
    public String getTransactionCategory() {
        return this.transactionCategory;
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
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return this.errorMessage;
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
     * Sets the transaction ID.
     *
     * @param id the new transaction ID
     */
    public void setId(String id) {
        this.id = id;
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
     * Sets the transaction description.
     *
     * @param transactionDescription the new transaction description
     */
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    /**
     * Sets the transaction category.
     *
     * @param transactionCategory the new transaction category
     */
    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
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

    /**
     * Sets the error message.
     *
     * @param error the new error message
     */
    public void setErrorMessage(String error) {
        this.errorMessage = error;
    }

    /**
     * Sets the success message.
     *
     * @param successMessage the new success message
     */
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
