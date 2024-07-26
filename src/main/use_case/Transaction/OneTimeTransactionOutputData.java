package use_case.Transaction;

import entity.OneTimeOutflow;
import entity.OneTimeInflow;

import java.time.LocalDate;

/**
 * The OneTimeTransactionOutputData class represents the output data of a one-time transaction operation.
 * It includes details such as the new balance, transaction amount, date, description, category, and status of the use case.
 *
 * @author Dana
 * @author Eric
 */
public class OneTimeTransactionOutputData {

    private float newBalance;
    private String id;
    private float amount;
    private LocalDate transactionDate;
    private String transactionDescription;
    private String transactionCategory;

    private boolean useCaseFailed;

    /**
     * Constructs a OneTimeTransactionOutputData object for an outflow transaction with the specified details.
     *
     * @param oneTimeOutflow the outflow transaction entity
     * @param newBalance     the new balance after the transaction
     */
    public OneTimeTransactionOutputData(OneTimeOutflow oneTimeOutflow, float newBalance) {
        this.newBalance = newBalance;
        this.amount = oneTimeOutflow.getAmount();
        this.id = oneTimeOutflow.getIdentification();
        this.transactionDate = oneTimeOutflow.getDate();
        this.transactionDescription = oneTimeOutflow.getDescription();
        this.transactionCategory = oneTimeOutflow.getCategory();
        this.useCaseFailed = false;
    }

    /**
     * Constructs a OneTimeTransactionOutputData object for an inflow transaction with the specified details.
     *
     * @param oneTimeInflow the inflow transaction entity
     * @param newBalance    the new balance after the transaction
     */
    public OneTimeTransactionOutputData(OneTimeInflow oneTimeInflow, float newBalance) {
        this.newBalance = newBalance;
        this.amount = oneTimeInflow.getAmount();
        this.id = oneTimeInflow.getIdentification();
        this.transactionDate = oneTimeInflow.getDate();
        this.transactionDescription = oneTimeInflow.getDescription();
        this.transactionCategory = oneTimeInflow.getCategory();
        this.useCaseFailed = false;
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
     * Gets the identification of the transaction.
     *
     * @return the identification of the transaction
     */
    public String getId() { return this.id; }

    /**
     * Gets the amount of the transaction.
     *
     * @return the amount of the transaction
     */
    public float getAmount() { return this.amount; }

    /**
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return the description of the transaction
     */
    public String getTransactionDescription() {
            return this.transactionDescription;
    }

    /**
     * Gets the category of the transaction.
     *
     * @return the category of the transaction
     */
    public String getTransactionCategory() {
            return this.transactionCategory;
    }

    /**
     * Checks if the use case has failed.
     *
     * @return true if the use case has failed, false otherwise
     */
    public boolean isUseCaseFailed() {
        return this.useCaseFailed;
    }

    /**
     * Sets the status of the use case failure.
     *
     * @param useCaseFailed the new status of the use case failure
     */
    public void setUseCaseFailed(boolean useCaseFailed) { this.useCaseFailed = useCaseFailed; }

    /**
     * Sets the date of the transaction.
     *
     * @param transactionDate the new date of the transaction
     */
    public void setTransactionDate(LocalDate transactionDate) {
            this.transactionDate = transactionDate;
    }
}