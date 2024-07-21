package use_case;

import entity.OneTimeOutflow;
import entity.OneTimeInflow;

import java.time.LocalDate;

public class OneTimeTransactionOutputData {

    private float newBalance;
    private String id;
    private float amount;
    private LocalDate transactionDate;
    private String transactionDescription;
    private String transactionCategory;

    private boolean useCaseFailed;

    // Constructor for outflow
    public OneTimeTransactionOutputData(OneTimeOutflow oneTimeOutflow, float newBalance) {
        this.newBalance = newBalance;
        this.amount = oneTimeOutflow.getAmount();
        this.id = oneTimeOutflow.getIdentification();
        this.transactionDate = oneTimeOutflow.getDate();
        this.transactionDescription = oneTimeOutflow.getDescription();
        this.transactionCategory = oneTimeOutflow.getCategory();
        this.useCaseFailed = false;
    }
    // Constructor for inflow
    public OneTimeTransactionOutputData(OneTimeInflow oneTimeInflow, float newBalance) {
        this.newBalance = newBalance;
        this.amount = oneTimeInflow.getAmount();
        this.id = oneTimeInflow.getIdentification();
        this.transactionDate = oneTimeInflow.getDate();
        this.transactionDescription = oneTimeInflow.getDescription();
        this.transactionCategory = oneTimeInflow.getCategory();
        this.useCaseFailed = false;
    }

    // getters
    public float getNewBalance() {
            return this.newBalance;
        }
    public String getId() { return this.id; }
    public float getAmount() { return this.amount; }
    public LocalDate getTransactionDate() {
        return this.transactionDate;
        }
    public String getTransactionDescription() {
            return this.transactionDescription;
        }
    public String getTransactionCategory() {
            return this.transactionCategory;
        }
    public boolean isUseCaseFailed() {
        return this.useCaseFailed;
    }

    // setters
    public void setUseCaseFailed(boolean useCaseFailed) { this.useCaseFailed = useCaseFailed; }
    public void setTransactionDate(LocalDate transactionDate) {
            this.transactionDate = transactionDate;
        }
}